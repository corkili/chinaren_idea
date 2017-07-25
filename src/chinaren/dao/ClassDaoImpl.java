package chinaren.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import chinaren.model.Class;
import chinaren.model.Result;

/**
 * 班级数据持久层实现类
 * @ClassName ClassDaoImpl
 * @author 李浩然
 * @date 2017年7月20日
 * @version 1.0
 */
@Repository
public class ClassDaoImpl extends BaseDao implements ClassDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private AttendDao attendDao;
	
	
	private Logger logger = Logger.getLogger(ClassDaoImpl.class);

	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss - ");
	
	/**
	 * 构造函数
	 */
	public ClassDaoImpl() {
		
	}

	/**
	 * @see chinaren.dao.ClassDao#selectClassByClassId(long)
	 */
	@Override
	public Result<Class> selectClassByClassId(long classId) {
		logger.info(dateFormat.format(new Date()) + "action: select a class by class id");
		String sql = "select * from " + TABLE_CLASS + " where " + COL_CLASS_ID + "=?";
		logger.info(dateFormat.format(new Date()) + "sql: " + sql);
		Class clazz = null;
		boolean successful = false;
		String message = "";
		try {
			RowMapper<Class> rowMapper = BeanPropertyRowMapper.newInstance(Class.class);
			Object[] params = { classId };
			clazz = jdbcTemplate.queryForObject(sql, params, rowMapper);
			successful = clazz != null;
			message = successful ? "select<successful>" : "select<failed>";
			if (successful) {
				Result<List<Long>> result = attendDao.selectUserIdByClassId(clazz.getClassId(), STATUS_TRUE);
				message = message + " and " + result.getMessage();
				clazz.setClassmates(result.getResult());
				result = attendDao.selectUserIdByClassId(clazz.getClassId(), STATUS_FALSE);
				message += " and " + result.getMessage();
				clazz.setNotApplys(result.getResult());
			}
		} catch (Exception e) {
			successful = false;
			message = "select<failed>";
		}
		logger.info(dateFormat.format(new Date()) + "result: " + message);
		return new Result<Class>(successful, message, clazz);
	}

	/**
	 * @see chinaren.dao.ClassDao#selectClass(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Result<Class> selectClass(String province, String city, String area, 
			String school, String gradeYear, String className) {
		logger.info(dateFormat.format(new Date()) + "action: select a class by class information");
		String sql = "select * from " + TABLE_CLASS + " where " + COL_PROVINCE + "=? and "
				+ COL_CITY + "=? and " + COL_AREA + "=? and " + COL_SCHOOL + "=? and " 
				+ COL_GRADE_YEAR + "=? and " + COL_CLASS_NAME + "=?";
		logger.info(dateFormat.format(new Date()) + "sql: " + sql);
		Class clazz = null;
		boolean successful = false;
		String message = "";
		try {
			RowMapper<Class> rowMapper = BeanPropertyRowMapper.newInstance(Class.class);
			Object[] params = { province, city, area, school, gradeYear, className };
			clazz = jdbcTemplate.queryForObject(sql, params, rowMapper);
			successful = clazz != null;
			message = successful ? "select<successful>" : "select<failed>";
			if (successful) {
				Result<List<Long>> result = attendDao.selectUserIdByClassId(clazz.getClassId(), STATUS_TRUE);
				message = message + " and " + result.getMessage();
				clazz.setClassmates(result.getResult());
				result = attendDao.selectUserIdByClassId(clazz.getClassId(), STATUS_FALSE);
				message += " and " + result.getMessage();
				clazz.setNotApplys(result.getResult());
			}
		} catch (Exception e) {
			successful = false;
			message = "select<failed>";
		}
		logger.info(dateFormat.format(new Date()) + "result: " + message);
		return new Result<Class>(successful, message, clazz);
	}

	/**
	 * @see chinaren.dao.ClassDao#selectClassesByManagerId(long)
	 */
	@Override
	public Result<List<Class>> selectClassesByManagerId(long managerId) {
		logger.info(dateFormat.format(new Date()) + "action: select classes by manager id");
		String sql = "select * from " + TABLE_CLASS + " where " + COL_MANAGER_ID + "=?";
		logger.info(dateFormat.format(new Date()) + "sql: " + sql);
		List<Class> classes = null;
		boolean successful = false;
		String message = "";
		try {
			RowMapper<Class> rowMapper = BeanPropertyRowMapper.newInstance(Class.class);
			Object[] params = { managerId };
			classes = jdbcTemplate.query(sql, params, rowMapper);
			classes = classes != null ? classes : new ArrayList<Class>();
			successful = true;
			message = "select<successful>";
			for (Class clazz : classes) {
				Result<List<Long>> result = attendDao.selectUserIdByClassId(clazz.getClassId(), STATUS_TRUE);
				clazz.setClassmates(result.getResult());
				result = attendDao.selectUserIdByClassId(clazz.getClassId(), STATUS_FALSE);
				clazz.setNotApplys(result.getResult());
			}
		} catch (DataAccessException e) {
			successful = false;
			message = "select<failed>";
			classes = new ArrayList<Class>();
		}
		logger.info(dateFormat.format(new Date()) + "result: " + message);
		return new Result<List<Class>>(successful, message, classes);
	}

	/**
	 * @see chinaren.dao.ClassDao#selectClasses()
	 */
	@Override
	public Result<List<Class>> selectClasses() {
		logger.info(dateFormat.format(new Date()) + "action: select all classes");
		String sql = "select * from " + TABLE_CLASS;
		logger.info(dateFormat.format(new Date()) + "sql: " + sql);
		List<Class> classes = null;
		boolean successful = false;
		String message = "";
		try {
			RowMapper<Class> rowMapper = BeanPropertyRowMapper.newInstance(Class.class);
			Object[] params = { };
			classes = jdbcTemplate.query(sql, params, rowMapper);
			classes = classes != null ? classes : new ArrayList<Class>();
			successful = true;
			message = "select<successful>";
			for (Class clazz : classes) {
				Result<List<Long>> result = attendDao.selectUserIdByClassId(clazz.getClassId(), STATUS_TRUE);
				clazz.setClassmates(result.getResult());
				result = attendDao.selectUserIdByClassId(clazz.getClassId(), STATUS_FALSE);
				clazz.setNotApplys(result.getResult());
			}
		} catch (DataAccessException e) {
			successful = false;
			message = "select<failed>";
			classes = new ArrayList<Class>();
		}
		logger.info(dateFormat.format(new Date()) + "result: " + message);
		return new Result<List<Class>>(successful, message, classes);
	}
	
	/**
	 * @see chinaren.dao.ClassDao#selectClasses(java.lang.String)
	 */
	@Override
	public Result<List<Class>> selectClasses(String province) {
		logger.info(dateFormat.format(new Date()) + "action: select classes by class information");
		String sql = "select * from " + TABLE_CLASS + " where " + COL_PROVINCE + "=?";
		logger.info(dateFormat.format(new Date()) + "sql: " + sql);
		List<Class> classes = null;
		boolean successful = false;
		String message = "";
		try {
			RowMapper<Class> rowMapper = BeanPropertyRowMapper.newInstance(Class.class);
			Object[] params = { province };
			classes = jdbcTemplate.query(sql, params, rowMapper);
			classes = classes != null ? classes : new ArrayList<Class>();
			successful = true;
			message = "select<successful>";
			for (Class clazz : classes) {
				Result<List<Long>> result = attendDao.selectUserIdByClassId(clazz.getClassId(), STATUS_TRUE);
				clazz.setClassmates(result.getResult());
				result = attendDao.selectUserIdByClassId(clazz.getClassId(), STATUS_FALSE);
				clazz.setNotApplys(result.getResult());
			}
		} catch (DataAccessException e) {
			successful = false;
			message = "select<failed>";
			classes = new ArrayList<Class>();
		}
		logger.info(dateFormat.format(new Date()) + "result: " + message);
		return new Result<List<Class>>(successful, message, classes);
	}

	/**
	 * @see chinaren.dao.ClassDao#selectClasses(java.lang.String, java.lang.String)
	 */
	@Override
	public Result<List<Class>> selectClasses(String province, String city) {
		logger.info(dateFormat.format(new Date()) + "action: select classes by class information");
		String sql = "select * from " + TABLE_CLASS + " where " + COL_PROVINCE + "=? and "
				+ COL_CITY + "=?";
		logger.info(dateFormat.format(new Date()) + "sql: " + sql);
		List<Class> classes = null;
		boolean successful = false;
		String message = "";
		try {
			RowMapper<Class> rowMapper = BeanPropertyRowMapper.newInstance(Class.class);
			Object[] params = { province, city };
			classes = jdbcTemplate.query(sql, params, rowMapper);
			classes = classes != null ? classes : new ArrayList<Class>();
			successful = true;
			message = "select<successful>";
			for (Class clazz : classes) {
				Result<List<Long>> result = attendDao.selectUserIdByClassId(clazz.getClassId(), STATUS_TRUE);
				clazz.setClassmates(result.getResult());
				result = attendDao.selectUserIdByClassId(clazz.getClassId(), STATUS_FALSE);
				clazz.setNotApplys(result.getResult());
			}
		} catch (DataAccessException e) {
			successful = false;
			message = "select<failed>";
			classes = new ArrayList<Class>();
		}
		logger.info(dateFormat.format(new Date()) + "result: " + message);
		return new Result<List<Class>>(successful, message, classes);
	}

	/**
	 * @see chinaren.dao.ClassDao#selectClasses(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Result<List<Class>> selectClasses(String province, String city, String area) {
		logger.info(dateFormat.format(new Date()) + "action: select classes by class information");
		String sql = "select * from " + TABLE_CLASS + " where " + COL_PROVINCE + "=? and "
				+ COL_CITY + "=? and " + COL_AREA + "=?";
		logger.info(dateFormat.format(new Date()) + "sql: " + sql);
		List<Class> classes = null;
		boolean successful = false;
		String message = "";
		try {
			RowMapper<Class> rowMapper = BeanPropertyRowMapper.newInstance(Class.class);
			Object[] params = { province, city, area };
			classes = jdbcTemplate.query(sql, params, rowMapper);
			classes = classes != null ? classes : new ArrayList<Class>();
			successful = true;
			message = "select<successful>";
			for (Class clazz : classes) {
				Result<List<Long>> result = attendDao.selectUserIdByClassId(clazz.getClassId(), STATUS_TRUE);
				clazz.setClassmates(result.getResult());
				result = attendDao.selectUserIdByClassId(clazz.getClassId(), STATUS_FALSE);
				clazz.setNotApplys(result.getResult());
			}
		} catch (DataAccessException e) {
			successful = false;
			message = "select<failed>";
			classes = new ArrayList<Class>();
		}
		logger.info(dateFormat.format(new Date()) + "result: " + message);
		return new Result<List<Class>>(successful, message, classes);
	}

	/**
	 * @see chinaren.dao.ClassDao#selectClasses(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Result<List<Class>> selectClasses(String province, String city, String area, String school) {
		logger.info(dateFormat.format(new Date()) + "action: select classes by class information");
		String sql = "select * from " + TABLE_CLASS + " where " + COL_PROVINCE + "=? and "
				+ COL_CITY + "=? and " + COL_AREA + "=? and " + COL_SCHOOL + " like ?";
		logger.info(dateFormat.format(new Date()) + "sql: " + sql);
		List<Class> classes = null;
		boolean successful = false;
		String message = "";
		try {
			RowMapper<Class> rowMapper = BeanPropertyRowMapper.newInstance(Class.class);
			Object[] params = { province, city, area, "%" + school + "%" };
			classes = jdbcTemplate.query(sql, params, rowMapper);
			classes = classes != null ? classes : new ArrayList<Class>();
			successful = true;
			message = "select<successful>";
			for (Class clazz : classes) {
				Result<List<Long>> result = attendDao.selectUserIdByClassId(clazz.getClassId(), STATUS_TRUE);
				clazz.setClassmates(result.getResult());
				result = attendDao.selectUserIdByClassId(clazz.getClassId(), STATUS_FALSE);
				clazz.setNotApplys(result.getResult());
			}
		} catch (DataAccessException e) {
			successful = false;
			message = "select<failed>";
			classes = new ArrayList<Class>();
		}
		logger.info(dateFormat.format(new Date()) + "result: " + message);
		return new Result<List<Class>>(successful, message, classes);
	}

	/**
	 * @see chinaren.dao.ClassDao#selectClasses(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Result<List<Class>> selectClasses(String province, String city, String area, String school,
			String gradeYear) {
		logger.info(dateFormat.format(new Date()) + "action: select classes by class information");
		String sql = "select * from " + TABLE_CLASS + " where " + COL_PROVINCE + "=? and "
				+ COL_CITY + "=? and " + COL_AREA + "=? and " + COL_SCHOOL + " like ? and " 
				+ COL_GRADE_YEAR + " like ?";
		logger.info(dateFormat.format(new Date()) + "sql: " + sql);
		List<Class> classes = null;
		boolean successful = false;
		String message = "";
		try {
			RowMapper<Class> rowMapper = BeanPropertyRowMapper.newInstance(Class.class);
			Object[] params = { province, city, area, "%" + school + "%", "%" + gradeYear + "%" };
			classes = jdbcTemplate.query(sql, params, rowMapper);
			classes = classes != null ? classes : new ArrayList<Class>();
			successful = true;
			message = "select<successful>";
			for (Class clazz : classes) {
				Result<List<Long>> result = attendDao.selectUserIdByClassId(clazz.getClassId(), STATUS_TRUE);
				clazz.setClassmates(result.getResult());
				result = attendDao.selectUserIdByClassId(clazz.getClassId(), STATUS_FALSE);
				clazz.setNotApplys(result.getResult());
			}
		} catch (DataAccessException e) {
			successful = false;
			message = "select<failed>";
			classes = new ArrayList<Class>();
		}
		logger.info(dateFormat.format(new Date()) + "result: " + message);
		return new Result<List<Class>>(successful, message, classes);
	}

	/**
	 * @see chinaren.dao.ClassDao#selectClasses(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Result<List<Class>> selectClasses(String province, String city, String area, 
			String school, String gradeYear, String className) {
		logger.info(dateFormat.format(new Date()) + "action: select classes by class information");
		String sql = "select * from " + TABLE_CLASS + " where " + COL_PROVINCE + "=? and "
				+ COL_CITY + "=? and " + COL_AREA + "=? and " + COL_SCHOOL + " like ? and " 
				+ COL_GRADE_YEAR + " like ? and " + COL_CLASS_NAME + " like ?";
		logger.info(dateFormat.format(new Date()) + "sql: " + sql);
		List<Class> classes = null;
		boolean successful = false;
		String message = "";
		try {
			RowMapper<Class> rowMapper = BeanPropertyRowMapper.newInstance(Class.class);
			Object[] params = { province, city, area, "%" + school + "%", 
					"%" + gradeYear + "%", "%" + className + "%" };
			classes = jdbcTemplate.query(sql, params, rowMapper);
			classes = classes != null ? classes : new ArrayList<Class>();
			successful = true;
			message = "select<successful>";
			for (Class clazz : classes) {
				Result<List<Long>> result = attendDao.selectUserIdByClassId(clazz.getClassId(), STATUS_TRUE);
				clazz.setClassmates(result.getResult());
				result = attendDao.selectUserIdByClassId(clazz.getClassId(), STATUS_FALSE);
				clazz.setNotApplys(result.getResult());
			}
		} catch (DataAccessException e) {
			successful = false;
			message = "select<failed>";
			classes = new ArrayList<Class>();
		}
		logger.info(dateFormat.format(new Date()) + "result: " + message);
		return new Result<List<Class>>(successful, message, classes);
	}

	/**
	 * @see chinaren.dao.ClassDao#insertClass(chinaren.model.Class)
	 */
	@Override
	public Result<Class> insertClass(Class clazz) {
		logger.info(dateFormat.format(new Date()) + "action: insert a new class");
		String sql = "insert into " + TABLE_CLASS + " ("
				+ COL_SCHOOL + "," + COL_CLASS_NAME + "," + COL_GRADE_YEAR + ","
				+ COL_DESCRIPTION + "," + COL_PROVINCE + "," + COL_CITY + ","
				+ COL_AREA + "," + COL_MANAGER_ID + ") values(?,?,?,?,?,?,?,?)";
		logger.info(dateFormat.format(new Date()) + "sql: " + sql);
		Class newClass = null;
		boolean successful = false;
		String message = "";
		try {
			Object[] params = { clazz.getSchool(), clazz.getClassName(), clazz.getGradeYear(),
					clazz.getDescription(), clazz.getProvince(), clazz.getCity(),
					clazz.getArea(), clazz.getManagerId()};
			successful = jdbcTemplate.update(sql, params) == 1;
			message = successful ? "insert<successful>" : "insert<failed>";
			if (successful) {
				Result<Class> result = selectClass(clazz.getProvince(), 
						clazz.getCity(), clazz.getArea(), clazz.getSchool(),
						clazz.getGradeYear(), clazz.getClassName());
				message = message + " and " + result.getMessage();
				newClass = result.getResult();
				// 添加关系
				if (attendDao.insertAttend(newClass.getManagerId(), newClass.getClassId()).isSuccessful()
						&& attendDao.updateAttendStatus(newClass.getManagerId(), newClass.getClassId()).isSuccessful()) {
					result = selectClassByClassId(newClass.getClassId());
					successful = result.isSuccessful();
					message += " and insert<successful>";
					newClass = result.getResult();
				} else {
					deleteClass(newClass.getClassId());
					successful = false;
					newClass = null;
					message += " and insert<failed>";
				}
			}
		} catch (Exception e) {
			successful = false;
			message = "insert<failed>";
		}
		logger.info(dateFormat.format(new Date()) + "result: " + message);
		return new Result<Class>(successful, message, newClass);
	}

	/**
	 * @see chinaren.dao.ClassDao#deleteClass(long)
	 */
	@Override
	public Result<Boolean> deleteClass(long classId) {
		logger.info(dateFormat.format(new Date()) + "action: delete class by class id");
		String sql = "delete from " + TABLE_CLASS + " where " + COL_CLASS_ID + "=?";
		logger.info(dateFormat.format(new Date()) + "sql: " + sql);
		boolean successful = false;
		String message = "";
		try {
			Object[] params = { classId };
			successful = jdbcTemplate.update(sql, params) == 1;
			successful &= attendDao.deleteAttendByClassId(classId).isSuccessful();
			message = successful ? "delete<successful>" : "delete<failed>";
		} catch (Exception e) {
			successful = false;
			message = "delete<failed>";
		}
		logger.info(dateFormat.format(new Date()) + "result: " + message);
		return new Result<Boolean>(successful, message, successful);
	}

	/**
	 * @see chinaren.dao.ClassDao#updateDescription(long, java.lang.String)
	 */
	@Override
	public Result<Class> updateDescription(long classId, String description) {
		logger.info(dateFormat.format(new Date()) + "action: update class's description");
		String sql = "update " + TABLE_CLASS + " set " + COL_DESCRIPTION + "=? where " + COL_CLASS_ID + "=?";
		logger.info(dateFormat.format(new Date()) + "sql: " + sql);
		Class clazz = null;
		boolean successful = false;
		String message = "";
		try {
			Object[] params = { description, classId };
			successful = jdbcTemplate.update(sql, params) == 1;
			message = successful ? "update<successful>" : "update<failed>";
			if (successful) {
				Result<Class> result = selectClassByClassId(classId);
				clazz = result.getResult();
				successful = result.isSuccessful();
				message = message + " and " + result.getMessage();
			}
		} catch (Exception e) {
			successful = false;
			message = "update<failed>";
		}
		logger.info(dateFormat.format(new Date()) + "result: " + message);
		return new Result<Class>(successful, message, clazz);
	}

}
