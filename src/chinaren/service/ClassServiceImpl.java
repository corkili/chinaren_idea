package chinaren.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import chinaren.model.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import chinaren.service.UserServiceImpl.UserContext;
import chinaren.dao.AttendDao;
import chinaren.dao.BaseDao;
import chinaren.dao.ClassDao;
import chinaren.model.Class;
import chinaren.model.Result;

/**
 * 班级相关数据处理，服务层实现类
 * @ClassName ClassServiceImpl 
 * @author 李浩然
 * @date 2017年7月21日
 * @version 1.0
 */
@Service
public class ClassServiceImpl implements ClassService {

	@Autowired
	private ClassDao classDao;
	
	@Autowired
	private AttendDao attendDao;
	
	private Logger logger = Logger.getLogger(ClassServiceImpl.class);
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss - ");

	@Autowired
	private UserService userService;

	/**
	 * 构造方法
	 */
	public ClassServiceImpl() {
		// logger.info(dateFormat.format(new Date()) + "");
	}

	/**
	 * @see chinaren.service.ClassService#getClassInformation(long)
	 */
	@Override
	public Result<Class> getClassInformation(long classId) {
		logger.info(dateFormat.format(new Date()) + "get class's information - class " + classId);
		return classDao.selectClassByClassId(classId);
	}

	/**
	 * @see chinaren.service.ClassService#getClasses(long, boolean)
	 */
	@Override
	public Result<List<Class>> getClasses(long userId, boolean status) {
		logger.info(dateFormat.format(new Date()) + "get user's class list - user " + userId + "::" + status);
		Result<List<Long>> result = attendDao.selectClassIdByUserId(
				userId, status ? BaseDao.STATUS_TRUE : BaseDao.STATUS_FALSE);
		if (!result.isSuccessful()) {
			logger.info(dateFormat.format(new Date()) + "get user's class list: failed - user " + userId + "::" + status);
			return new Result<List<Class>>(false, "获取列表时发生错误", new ArrayList<Class>());
		}
		List<Class> classes = new ArrayList<Class>();
		for (long classId : result.getResult()) {
			Result<Class> result2 = classDao.selectClassByClassId(classId);
			if (result2.isSuccessful()) {
				classes.add(result2.getResult());
			}
		}
		logger.info(dateFormat.format(new Date()) + "get user's class list: successful - user " + userId + "::" + status);
		return new Result<List<Class>>(true, "获取班级列表成功", classes);
	}

	/**
	 * @see chinaren.service.ClassService#getManageClasses(long)
	 */
	@Override
	public Result<List<Class>> getManageClasses(long managerId) {
		logger.info(dateFormat.format(new Date()) + "get manager's class list - user " + managerId);
		return classDao.selectClassesByManagerId(managerId);
	}

	/**
	 * @see chinaren.service.ClassService#searchClasses(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Result<List<Class>> searchClasses(String province, String city, String area, String school, String gradeYear,
			String className) {

		logger.info(dateFormat.format(new Date()) + "search classes - " + province + "::" + city + "::" + area
				+ "::" + school + "::" + gradeYear + "::" + className);
		return classDao.selectClasses(province, city, area, school, gradeYear, className);
	}

	/**
	 * @see chinaren.service.ClassService#createClass(chinaren.model.Class)
	 */
	@Override
	public Result<Boolean> createClass(Class clazz) {
		// 验证实体是否为空
		if (clazz == null) {
			logger.info(dateFormat.format(new Date()) + "create class: failed - class's data invalid");
			return new Result<Boolean>(false, "班级数据异常", false);
		}
		
		if (clazz.getSchool() == null
				|| clazz.getSchool().trim().equals("")) {
			logger.info(dateFormat.format(new Date()) + "create class: failed - school invalid");
			return new Result<Boolean>(false, "学校不能为空", false);
		}
		
		if (clazz.getGradeYear() == null
				|| clazz.getGradeYear().trim().equals("")) {
			logger.info(dateFormat.format(new Date()) + "create class: failed - grade year invalid");
			return new Result<Boolean>(false, "年级不能为空", false);
		}
		
		if (clazz.getClassName() == null
				|| clazz.getClassName().trim().equals("")) {
			logger.info(dateFormat.format(new Date()) + "create class: failed - class name invalid");
			return new Result<Boolean>(false, "班级名称不能为空", false);
		}
		
		if (clazz.getProvince() == null || clazz.getProvince().trim().equals("")
				|| clazz.getCity() == null || clazz.getCity().trim().equals("")
				|| clazz.getArea() == null || clazz.getArea().trim().equals("")) {
			logger.info(dateFormat.format(new Date()) + "create class: failed - address invalid");
			return new Result<Boolean>(false, "地址不能为空", false);
		}
		
		if (classDao.selectClass(clazz.getProvince().trim(), clazz.getCity().trim(), 
				clazz.getArea().trim(), clazz.getSchool().trim(),clazz.getGradeYear().trim(), 
				clazz.getClassName().trim()).isSuccessful()) {
			logger.info(dateFormat.format(new Date()) + "create class: failed - exist class");
			return new Result<Boolean>(false, "已存在相同名称的班级", false);
		}
		
		if (clazz.getDescription() == null || clazz.getDescription().trim().equals("")) {
			clazz.setDescription("班长很懒，未留下任何东西！");
		}
		
		Result<Class> result = classDao.insertClass(clazz); 
		
		if (!result.isSuccessful()) {
			logger.info(dateFormat.format(new Date()) + "create class: failed - database error");
			return new Result<Boolean>(false, "数据库错误，请重试！", false);
		}
		logger.info(dateFormat.format(new Date()) + "create class: successful - class " + result.getResult().getClassId());
		userService.getUserContext().update(result.getResult().getManagerId());
		return new Result<Boolean>(true, "创建班级成功", true);
	}

	/**
	 * @see chinaren.service.ClassService#applyJoinClass(long, long)
	 */
	@Override
	public Result<Boolean> applyJoinClass(long userId, long classId) {
		logger.info(dateFormat.format(new Date()) + "apply to join class - " + userId + " join " + classId);
		Result<Class> result = classDao.selectClassByClassId(classId);
		if (!result.isSuccessful()) {
			logger.info(dateFormat.format(new Date()) + "apply to join class: failed - " + userId + " join " + classId);
			return new Result<Boolean>(false, "不存在相应的班级", false);
		}
		if (result.getResult().getClassmates().contains(userId) || result.getResult().getNotApplys().contains(userId)) {
			logger.info(dateFormat.format(new Date()) + "apply to join class: failed - " + userId + " join " + classId);
			return new Result<Boolean>(false, "已加入班级或已申请加入班级", false);
		}
		if (!attendDao.insertAttend(userId, classId).isSuccessful()) {
			logger.info(dateFormat.format(new Date()) + "apply to join class: failed - " + userId + " join " + classId);
			return new Result<Boolean>(false, "数据库错误，请重试", false);
		}
		logger.info(dateFormat.format(new Date()) + "apply to join class: successful - " + userId + " join " + classId);
		userService.getUserContext().update(userId, result.getResult().getManagerId());
		return new Result<Boolean>(true, "申请加入班级成功", true);
	}

	/**
	 * @see chinaren.service.ClassService#exitClass(long, long)
	 */
	@Override
	public Result<Boolean> exitClass(long userId, long classId) {
		logger.info(dateFormat.format(new Date()) + "exit class - " + userId + " exit " + classId);
		Result<Class> result = classDao.selectClassByClassId(classId);
		if (!result.isSuccessful()) {
			logger.info(dateFormat.format(new Date()) + "exit class: failed - " + userId + " exit " + classId);
			return new Result<Boolean>(false, "不存在相应的班级", false);
		}
		if (!attendDao.deleteAttend(userId, classId).isSuccessful()) {
			logger.info(dateFormat.format(new Date()) + "exit class: failed - " + userId + " exit " + classId);
			return new Result<Boolean>(false, "数据库错误，请重试", false);
		}
		logger.info(dateFormat.format(new Date()) + "exit class: successful - " + userId + " exit " + classId);
		userService.getUserContext().update(userId, result.getResult().getManagerId());
		return new Result<Boolean>(true, "退出班级成功", true);
	}

	@Override
	public Result<List<User>> getUsersByClassId(long classId, boolean status) {
		Result<Class> classResult = classDao.selectClassByClassId(classId);
		if (!classResult.isSuccessful()) {
			return new Result<>(false, "数据库错误，请重试！", new ArrayList<>());
		} else {
			List<User> userList = new ArrayList<>();
			for (long userId : status ? classResult.getResult().getClassmates() : classResult.getResult().getNotApplys()) {
				userList.add(userService.getUserInformation(userId).getResult());
			}
			return new Result<>(true, "获取成功！", userList);
		}
	}

	/**
	 * @see chinaren.service.ClassService#allowJoinClass(long, long, long)
	 */
	@Override
	public Result<Boolean> allowJoinClass(long managerId, long userId, long classId) {
		logger.info(dateFormat.format(new Date()) + "allow join class - " + managerId 
				+ " allow " + userId + " join " + classId);
		Result<Class> result = classDao.selectClassByClassId(classId);
		if (!result.isSuccessful()) {
			logger.info(dateFormat.format(new Date()) + "allow join class: failed - " + managerId 
					+ " allow " + userId + " join " + classId);
			return new Result<Boolean>(false, "不存在相应的班级", false);
		}
		if (!result.getResult().isManager(managerId)) {
			logger.info(dateFormat.format(new Date()) + "allow join class: failed - " + managerId 
					+ " allow " + userId + " join " + classId);
			return new Result<Boolean>(false, "非班级管理员不能进行此操作", false);
		}
		if (!attendDao.updateAttendStatus(userId, classId).isSuccessful()) {
			logger.info(dateFormat.format(new Date()) + "allow join class: failed - " + managerId 
					+ " allow " + userId + " join " + classId);
			return new Result<Boolean>(false, "数据库错误，请重试", false);
		}
		logger.info(dateFormat.format(new Date()) + "allow join class: successful - " + managerId 
				+ " allow " + userId + " join " + classId);
		userService.getUserContext().update(userId, managerId);
		return new Result<Boolean>(true, "允许加入班级成功", true);
	}

	/**
	 * @see chinaren.service.ClassService#refuseJoinClass(long, long, long)
	 */
	@Override
	public Result<Boolean> refuseJoinClass(long managerId, long userId, long classId) {
		logger.info(dateFormat.format(new Date()) + "refuse join class - " + managerId 
				+ " refuse " + userId + " join " + classId);
		Result<Class> result = classDao.selectClassByClassId(classId);
		if (!result.isSuccessful()) {
			logger.info(dateFormat.format(new Date()) + "refuse join class: failed - " + managerId 
					+ " refuse " + userId + " join " + classId);
			return new Result<Boolean>(false, "不存在相应的班级", false);
		}
		if (!result.getResult().isManager(managerId)) {
			logger.info(dateFormat.format(new Date()) + "refuse join class: failed - " + managerId 
					+ " refuse " + userId + " join " + classId);
			return new Result<Boolean>(false, "非班级管理员不能进行此操作", false);
		}
		if (!attendDao.deleteAttend(userId, classId).isSuccessful()) {
			logger.info(dateFormat.format(new Date()) + "refuse join class: failed - " + managerId 
					+ " refuse " + userId + " join " + classId);
			return new Result<Boolean>(false, "数据库错误，请重试", false);
		}
		logger.info(dateFormat.format(new Date()) + "refuse join class: successful - " + managerId 
				+ " refuse " + userId + " join " + classId);
		userService.getUserContext().update(userId, managerId);
		return new Result<Boolean>(true, "拒绝加入班级成功", true);
	}

	/**
	 * @see chinaren.service.ClassService#removeClassmate(long, long, long)
	 */
	@Override
	public Result<Boolean> removeClassmate(long managerId, long userId, long classId) {
		logger.info(dateFormat.format(new Date()) + "remove classmate from class - " + managerId 
				+ " remove " + userId + " from " + classId);
		Result<Class> result = classDao.selectClassByClassId(classId);
		if (!result.isSuccessful()) {
			logger.info(dateFormat.format(new Date()) + "remove classmate from class: failed - " + managerId 
					+ " remove " + userId + " from " + classId);
			return new Result<Boolean>(false, "不存在相应的班级", false);
		}
		if (!result.getResult().isManager(managerId)) {
			logger.info(dateFormat.format(new Date()) + "remove classmate from class: failed - " + managerId 
					+ " remove " + userId + " from " + classId);
			return new Result<Boolean>(false, "非班级管理员不能进行此操作", false);
		}
		if (!attendDao.deleteAttend(userId, classId).isSuccessful()) {
			logger.info(dateFormat.format(new Date()) + "remove classmate from class: failed - " + managerId 
					+ " remove " + userId + " from " + classId);
			return new Result<Boolean>(false, "数据库错误，请重试", false);
		}
		logger.info(dateFormat.format(new Date()) + "remove classmate from class: successful - " + managerId 
				+ " remove " + userId + " from " + classId);
		userService.getUserContext().update(userId, managerId);
		return new Result<Boolean>(true, "移除班级成员成功", true);
	}

	/**
	 * @see chinaren.service.ClassService#removeClass(long, long)
	 */
	@Override
	public Result<Boolean> removeClass(long managerId, long classId) {
		logger.info(dateFormat.format(new Date()) + "remove class - manager " + managerId 
				+ " remove class " + classId);
		Result<Class> result = classDao.selectClassByClassId(classId);
		if (!result.isSuccessful()) {
			logger.info(dateFormat.format(new Date()) + "remove class: failed - manager " + managerId 
					+ " remove class " + classId);
			return new Result<Boolean>(false, "不存在相应的班级", false);
		}
		if (!result.getResult().isManager(managerId)) {
			logger.info(dateFormat.format(new Date()) + "remove class: failed - manager " + managerId 
					+ " remove class " + classId);
			return new Result<Boolean>(false, "非班级管理员不能进行此操作", false);
		}
		if (!classDao.deleteClass(classId).isSuccessful()) {
			logger.info(dateFormat.format(new Date()) + "remove class: failed - manager " + managerId 
					+ " remove class " + classId);
			return new Result<Boolean>(false, "数据库错误，请重试", false);
		}
		logger.info(dateFormat.format(new Date()) + "remove class: successful - manager " + managerId 
				+ " remove class " + classId);
		List<Long> userId = new ArrayList<Long>();
		userId.addAll(result.getResult().getClassmates());
		userId.addAll(result.getResult().getNotApplys());
		userService.getUserContext().update(userId);
		return new Result<Boolean>(true, "删除班级成功", true);
	}


	@Override
	public Result<Boolean> modifyDescription(long managerId, long classId, String description) {
        logger.info(dateFormat.format(new Date()) + "modify class's description - manager " + managerId
                + " modify " + classId);
        Result<Class> result = classDao.selectClassByClassId(classId);
        if (!result.isSuccessful()) {
            logger.info(dateFormat.format(new Date()) + "modify class's description: failed - manager " + managerId
                    + " modify " + classId);
            return new Result<Boolean>(false, "不存在相应的班级", false);
        }
        if (!result.getResult().isManager(managerId)) {
            logger.info(dateFormat.format(new Date()) + "modify class's description: failed - manager " + managerId
                    + " modify " + classId);
            return new Result<Boolean>(false, "非班级管理员不能进行此操作", false);
        }
        if (!classDao.updateDescription(classId, description).isSuccessful()) {
            logger.info(dateFormat.format(new Date()) + "modify class's description: failed - manager " + managerId
                    + " modify " + classId);
            return new Result<Boolean>(false, "数据库错误，请重试", false);
        }
        logger.info(dateFormat.format(new Date()) + "modify class's description: successful - manager " + managerId
                + " modify " + classId);
        return new Result<Boolean>(true, "修改班级简介成功", true);
	}

}
