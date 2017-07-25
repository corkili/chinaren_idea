package chinaren.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import chinaren.model.Result;

/**
 * 班级-同学关系数据持久层实现类
 * @ClassName AttendDaoImpl
 * @author 李浩然
 * @date 2017年7月20日
 * @version 1.0
 */
@Repository
public class AttendDaoImpl extends BaseDao implements AttendDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private Logger logger = Logger.getLogger(AttendDaoImpl.class);
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss - ");
	
	/**
	 * 构造函数
	 */
	public AttendDaoImpl() {
		
	}

	/**
	 * @see chinaren.dao.AttendDao#selectUserIdByClassId(long, String)
	 */
	@Override
	public Result<List<Long>> selectUserIdByClassId(long classId, String status) {
		logger.info(dateFormat.format(new Date()) + "action: select user ids by class id");
		String sql = "select " + COL_USER_ID + " from " + TABLE_ATTEND + " where "
				+ COL_CLASS_ID + "=? and " + COL_STATUS + "=?";
		logger.info(dateFormat.format(new Date()) + "sql: " + sql);
		List<Long> userIdList = null;
		boolean successful = false;
		String message = "";
		try {
			Object[] params = { classId, status };
			userIdList = jdbcTemplate.queryForList(sql, params, Long.class);
			userIdList = userIdList != null ? userIdList : new ArrayList<Long>();
			successful = true;
			message = successful ? "select<successful>" : "select<failed>";
		} catch (Exception e) {
			successful = false;
			message = "select<failed>";
			userIdList = new ArrayList<Long>();
		}
		logger.info(dateFormat.format(new Date()) + "result: " + message);
		return new Result<List<Long>>(successful, message, userIdList);
	}

	/**
	 * @see chinaren.dao.AttendDao#selectClassIdByUserId(long, String)
	 */
	@Override
	public Result<List<Long>> selectClassIdByUserId(long userId, String status) {
		logger.info(dateFormat.format(new Date()) + "action: select class ids by user id");
		String sql = "select " + COL_CLASS_ID + " from " + TABLE_ATTEND + " where "
				+ COL_USER_ID + "=? and " + COL_STATUS + "=?";
		logger.info(dateFormat.format(new Date()) + "sql: " + sql);
		List<Long> userIdList = null;
		boolean successful = false;
		String message = "";
		try {
			Object[] params = { userId, status };
			userIdList = jdbcTemplate.queryForList(sql, params, Long.class);
			userIdList = userIdList != null ? userIdList : new ArrayList<Long>();
			successful = true;
			message = successful ? "select<successful>" : "select<failed>";
		} catch (Exception e) {
			successful = false;
			message = "select<failed>";
			userIdList = new ArrayList<Long>();
		}
		logger.info(dateFormat.format(new Date()) + "result: " + message);
		return new Result<List<Long>>(successful, message, userIdList);
	}

	/**
	 * @see chinaren.dao.AttendDao#insertAttend(long, long)
	 */
	@Override
	public Result<Boolean> insertAttend(long userId, long classId) {
		logger.info(dateFormat.format(new Date()) + "action: insert a new attend");
		String sql = "insert into " + TABLE_ATTEND + " (" + COL_USER_ID 
				+ "," + COL_CLASS_ID + "," + COL_STATUS + ") values(?,?,?)";
		logger.info(dateFormat.format(new Date()) + "sql: " + sql);
		boolean successful = false;
		String message = "";
		try {
			Object[] params = { userId, classId, STATUS_FALSE };
			successful = jdbcTemplate.update(sql, params) == 1;
			message = successful ? "insert<successful>" : "insert<failed>";
		} catch (Exception e) {
			successful = false;
			message = "insert<exception>";
		}
		logger.info(dateFormat.format(new Date()) + "result: " + message);
		return new Result<Boolean>(successful, message, successful);
	}

	/**
	 * @see chinaren.dao.AttendDao#deleteAttend(long, long)
	 */
	@Override
	public Result<Boolean> deleteAttend(long userId, long classId) {
		logger.info(dateFormat.format(new Date()) + "action: delete a attend");
		String sql = "delete from " + TABLE_ATTEND + " where " + COL_USER_ID
				+ "=? and " + COL_CLASS_ID + "=?";
		logger.info(dateFormat.format(new Date()) + "sql: " + sql);
		boolean successful = false;
		String message = "";
		try {
			Object[] params = { userId, classId };
			successful = jdbcTemplate.update(sql, params) == 1;
			message = successful ? "delete<successful>" : "delete<failed>";
		} catch (Exception e) {
			successful = false;
			message = "delete<failed>";
		}
		logger.info(dateFormat.format(new Date()) + "result: " + message);
		return new Result<Boolean>(successful, message, successful);
	}

	/**
	 * @see chinaren.dao.AttendDao#deleteAttendByClassId(long)
	 */
	@Override
	public Result<Boolean> deleteAttendByClassId(long classId) {
		logger.info(dateFormat.format(new Date()) + "action: delete attends for a class");
		String sql = "delete from " + TABLE_ATTEND + " where " + COL_CLASS_ID + "=?";
		logger.info(dateFormat.format(new Date()) + "sql: " + sql);
		boolean successful = false;
		String message = "";
		try {
			Object[] params = { classId };
			successful = jdbcTemplate.update(sql, params) == 1;
			message = successful ? "delete<successful>" : "delete<failed>";
		} catch (Exception e) {
			successful = false;
			message = "delete<failed>";
		}
		logger.info(dateFormat.format(new Date()) + "result: " + message);
		return new Result<Boolean>(successful, message, successful);
	}

	/**
	 * @see chinaren.dao.AttendDao#updateAttendStatus(long, long)
	 */
	@Override
	public Result<Boolean> updateAttendStatus(long userId, long classId) {
		logger.info(dateFormat.format(new Date()) + "action: delete a attend");
		String sql = "update " + TABLE_ATTEND + " set " + COL_STATUS + "=?"
				+ " where " + COL_USER_ID + "=? and " + COL_CLASS_ID + "=?";
		logger.info(dateFormat.format(new Date()) + "sql: " + sql);
		boolean successful = false;
		String message = "";
		try {
			Object[] params = { STATUS_TRUE, userId, classId };
			successful = jdbcTemplate.update(sql, params) == 1;
			message = successful ? "update<successful>" : "update<failed>";
		} catch (Exception e) {
			successful = false;
			message = "update<failed>";
		}
		logger.info(dateFormat.format(new Date()) + "result: " + message);
		return new Result<Boolean>(successful, message, successful);
	}
}
