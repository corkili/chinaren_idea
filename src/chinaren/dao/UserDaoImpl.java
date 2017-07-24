package chinaren.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import chinaren.util.HeadImageUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import chinaren.model.Result;
import chinaren.model.User;

/**
 * 用户数据持久层实现类
 * @ClassName UserDaoImpl
 * @author 李浩然
 * @date 2017年7月20日
 * @version 1.0
 */
@Repository
public class UserDaoImpl extends BaseDao implements UserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private AttendDao attendDao;
	
	private Logger logger = Logger.getLogger(UserDaoImpl.class);
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss - ");
	
	/**
	 * 构造函数
	 */
	public UserDaoImpl() {
		
	}

	/**
	 * @see chinaren.dao.UserDao#selectUserByUserId(long)
	 */
	@Override
	public Result<User> selectUserByUserId(long userId) {
		logger.info(dateFormat.format(new Date()) + "action: select user by user id");
		String sql = "select * from " + TABLE_USER + " where " + COL_USER_ID + "=?";
		logger.info(dateFormat.format(new Date()) + "sql: " + sql);
		User user = null;
		boolean successful = false;
		String message = "";
		try {
			RowMapper<User> rowMapper = BeanPropertyRowMapper.newInstance(User.class);
			Object[] params = { userId };
			user = jdbcTemplate.queryForObject(sql, params, rowMapper);
			successful = user != null;
			message = successful ? "select<successful>" : "select<failed>";
			if (successful) {
				Result<List<Long>> result = attendDao.selectClassIdByUserId(user.getUserId(), STATUS_TRUE);
				message = message + " and " + result.getMessage();
				user.setBelongClass(result.getResult());
				result = attendDao.selectClassIdByUserId(user.getUserId(), STATUS_FALSE);
				message += " and " + result.getMessage();
				user.setApplyClass(result.getResult());
			}
		} catch (Exception e) {
			successful = false;
			message = "select<failed>";
		}
		logger.info(dateFormat.format(new Date()) + "result: " + message);
		return new Result<User>(successful, message, user);
	}

	/**
	 * @see chinaren.dao.UserDao#selectUserByEmail(java.lang.String)
	 */
	@Override
	public Result<User> selectUserByEmail(String email) {
		logger.info(dateFormat.format(new Date()) + "action: select user by email");
		String sql = "select * from " + TABLE_USER + " where " + COL_EMAIL + "=?";
		logger.info(dateFormat.format(new Date()) + "sql: " + sql);
		User user = null;
		boolean successful = false;
		String message = "";
		try {
			RowMapper<User> rowMapper = BeanPropertyRowMapper.newInstance(User.class);
			Object[] params = { email };
			user = jdbcTemplate.queryForObject(sql, params, rowMapper);
			successful = user != null;
			message = successful ? "select<successful>" : "select<failed>";
			if (successful) {
				Result<List<Long>> result = attendDao.selectClassIdByUserId(user.getUserId(), STATUS_TRUE);
				message = message + " and " + result.getMessage();
				user.setBelongClass(result.getResult());
				result = attendDao.selectClassIdByUserId(user.getUserId(), STATUS_FALSE);
				message += " and " + result.getMessage();
				user.setApplyClass(result.getResult());
			}
		} catch (Exception e) {
			successful = false;
			message = "select<failed>";
		}
		logger.info(dateFormat.format(new Date()) + "result: " + message);
		return new Result<User>(successful, message, user);
	}

	/**
	 * @see chinaren.dao.UserDao#selectUsersByClassId(long)
	 */
	@Override
	public Result<List<User>> selectUsersByClassId(long classId) {
		logger.info(dateFormat.format(new Date()) + "action: select users by class id");
		String sql = "select * from " + TABLE_USER + " where " + COL_CLASS_ID + "=?";
		logger.info(dateFormat.format(new Date()) + "sql: " + sql);
		List<User> users = null;
		boolean successful = false;
		String message = "";
		try {
			RowMapper<User> rowMapper = BeanPropertyRowMapper.newInstance(User.class);
			Object[] params = { classId };
			users = jdbcTemplate.query(sql, params, rowMapper);
			users = users != null ? users : new ArrayList<User>();
			successful = true;
			message = "select<successful>";
			for (User user : users) {
				Result<List<Long>> result = attendDao.selectClassIdByUserId(user.getUserId(), STATUS_TRUE);
				if (result.isSuccessful()) {
					user.setBelongClass(result.getResult());
					result = attendDao.selectClassIdByUserId(user.getUserId(), STATUS_FALSE);
					if (result.isSuccessful()) {
						user.setApplyClass(result.getResult());
					} else {
						user = null;
					}
				} else {
					user = null;
				}
			}
			users.removeAll(Collections.singleton(null));	// 移除null元素
		} catch (DataAccessException e) {
			successful = false;
			message = "select<failed>";
			users = new ArrayList<User>();
		}
		logger.info(dateFormat.format(new Date()) + "result: " + message);
		return new Result<List<User>>(successful, message, users);
	}

	/**
	 * @see chinaren.dao.UserDao#insertUser(chinaren.model.User)
	 */
	@Override
	public Result<User> insertUser(User user) {
		logger.info(dateFormat.format(new Date()) + "action: insert a new user");
		String sql = "insert into " + TABLE_USER + " ("
				+ COL_EMAIL + "," + COL_PASSWORD + "," + COL_NAME + ","
				+ COL_SEX + "," + COL_PHONE + "," + COL_INTRODUCTION + ","
				+ COL_HEAD_IMAGE + "," + COL_PROVINCE + "," + COL_CITY + ","
				+ COL_AREA + ") values(?,?,?,?,?,?,?,?,?,?)";
		logger.info(dateFormat.format(new Date()) + "sql: " + sql);
		User newUser = null;
		boolean successful = false;
		String message = "";
		try {
			Object[] params = { user.getEmail(), user.getPassword(), user.getName(), user.getSex(),
					user.getPhone(), user.getIntroduction(), user.getHeadImage(), user.getProvince(),
					user.getCity(), user.getArea() };
			successful = jdbcTemplate.update(sql, params) == 1;
			message = successful ? "insert<successful>" : "insert<failed>";
			if (successful) {
				Result<User> result = selectUserByEmail(user.getEmail());
				message = message + " and " + result.getMessage();
				newUser = result.getResult();
                newUser.setHeadImage(HeadImageUtil.BASE_NAME + newUser.getUserId() + HeadImageUtil.POSTFIX);
                jdbcTemplate.update("update " + TABLE_USER + " set " + COL_HEAD_IMAGE + "=?", newUser.getHeadImage());
			}
		} catch (Exception e) {
			successful = false;
			message = "insert<failed>";
		}
		logger.info(dateFormat.format(new Date()) + "result: " + message);
		return new Result<User>(successful, message, newUser);
	}

	/**
	 * @see chinaren.dao.UserDao#updatePassword(long, java.lang.String)
	 */
	@Override
	public Result<User> updatePassword(long userId, String password) {
		logger.info(dateFormat.format(new Date()) + "action: update user's password");
		String sql = "update " + TABLE_USER + " set " + COL_PASSWORD + "=? where " + COL_USER_ID + "=?";
		logger.info(dateFormat.format(new Date()) + "sql: " + sql);
		User user = null;
		boolean successful = false;
		String message = "";
		try {
			Object[] params = { password, userId };
			successful = jdbcTemplate.update(sql, params) == 1;
			message = successful ? "update<successful>" : "update<failed>";
			if (successful) {
				Result<User> result = selectUserByUserId(userId);
				user = result.getResult();
				successful = result.isSuccessful();
				message = message + " and " + result.getMessage();
			}
		} catch (Exception e) {
			successful = false;
			message = "update<failed>";
		}
		logger.info(dateFormat.format(new Date()) + "result: " + message);
		return new Result<User>(successful, message, user);
	}

	/**
	 * @see chinaren.dao.UserDao#updateUserInfo(chinaren.model.User)
	 */
	@Override
	public Result<User> updateUserInfo(User user) {
		logger.info(dateFormat.format(new Date()) + "action: update user's information");
		String sql = "update " + TABLE_USER + " set " + COL_NAME + "=?, "
				+ COL_SEX + "=?, " + COL_PHONE + "=?, " + COL_INTRODUCTION
				+ "=?, " + COL_PROVINCE + "=?, " + COL_CITY + "=?, "
				+ COL_AREA + "=? where" + COL_USER_ID + "=?";
		logger.info(dateFormat.format(new Date()) + "sql: " + sql);
		User newUser = null;
		boolean successful = false;
		String message = "";
		try {
			Object[] params = { user.getName(), user.getSex(), user.getPhone(), 
					user.getIntroduction(), user.getProvince(), user.getCity(),
					user.getArea(), user.getUserId() };
			successful = jdbcTemplate.update(sql, params) == 1;
			message = successful ? "update<successful>" : "update<failed>";
			if (successful) {
				Result<User> result = selectUserByUserId(user.getUserId());
				newUser = result.getResult();
				successful = result.isSuccessful();
				message = message + " and " + result.getMessage();
			}
		} catch (Exception e) {
			successful = false;
			message = "update<failed>";
		}
		logger.info(dateFormat.format(new Date()) + "result: " + message);
		return new Result<User>(successful, message, newUser);
	}

}
