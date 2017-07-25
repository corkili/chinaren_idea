package chinaren.service;

import java.util.List;

import chinaren.model.Class;
import chinaren.model.Result;
import chinaren.model.User;

/**
 * 班级相关数据处理，服务层接口
 * @ClassName ClassService 
 * @author 李浩然
 * @date 2017年7月21日
 * @version 1.0
 */
public interface ClassService {
	/**
	 * 获取班级信息服务
	 * @author 李浩然
	 * @param classId 待获取班级的ID
	 * @return 班级信息结果
	 */
	public Result<Class> getClassInformation(long classId);

	/**
	 * 获取用户班级列表服务
	 * @author 李浩然
	 * @param userId 用户ID
	 * @param status 状态：已加入（true)或待审核（false）
	 * @return 用户班级列表结果
	 */
	public Result<List<Class>> getClasses(long userId, boolean status);
	
	/**
	 * 获取所管理的班级列表服务
	 * @author 李浩然
	 * @param managerId 管理者ID
	 * @return 用户所管理的班级列表结果
	 */
	public Result<List<Class>> getManageClasses(long managerId);
	
	/**
	 * 搜索班级服务
	 * @author 李浩然
	 * @param province 省份
	 * @param city 城市
	 * @param area 地区
	 * @param school 学校
	 * @param gradeYear 年级
	 * @param className 班级名称
	 * @return 班级搜索结果列表
	 */
	public Result<List<Class>> searchClasses(
			String province,
			String city,
			String area,
			String school,
			String gradeYear,
			String className);
	
	/**
	 * 创建班级服务
	 * @author 李浩然
	 * @param clazz 待创建的班级
	 * @return 创建班级结果
	 */
	public Result<Boolean> createClass(Class clazz);
	
	/**
	 * 申请加入班级服务
	 * @author 李浩然
	 * @param userId 申请者用户ID
	 * @param classId 申请加入的班级ID
	 * @return 申请加入班级操作结果
	 */
	public Result<Boolean> applyJoinClass(long userId, long classId);
	
	/**
	 * 退出班级服务
	 * @author 李浩然
	 * @param userId 退出班级的用户ID
	 * @param classId 退出的班级ID
	 * @return 退出班级操作结果
	 */
	public Result<Boolean> exitClass(long userId, long classId);

	/**
	 * 获取班级成员服务
	 * @author 李浩然
	 * @param classId 班级的ID
	 * @param status 成员状态
	 * @return 班级成员列表
	 */
	public Result<List<User>> getUsersByClassId(long classId, boolean status);

	/**
	 * 批准加入班级服务
	 * @author 李浩然
	 * @param managerId 管理者用户ID
	 * @param userId 被批准加入用户的ID
	 * @param classId 班级的ID
	 * @return 批准加入班级操作结果
	 */
	public Result<Boolean> allowJoinClass(long managerId, long userId, long classId);
	
	/**
	 * 拒绝加入班级服务
	 * @author 李浩然
	 * @param managerId 管理者用户ID
	 * @param userId 被拒绝加入用户的ID
	 * @param classId 班级的ID
	 * @return 拒绝加入班级操作结果
	 */
	public Result<Boolean> refuseJoinClass(long managerId, long userId, long classId);
	
	/**
	 * 移除班级成员服务
	 * @author 李浩然
	 * @param managerId 管理者用户ID
	 * @param userId 被移除用户的ID
	 * @param classId 班级的ID
	 * @return 移除班级成员操作结果
	 */
	public Result<Boolean> removeClassmate(long managerId, long userId, long classId);
	
	/**
	 * 删除班级服务
	 * @author 李浩然
	 * @param managerId 管理者用户ID
	 * @param classId 班级的ID
	 * @return 删除班级操作结果
	 */
	public Result<Boolean> removeClass(long managerId, long classId);
	
	
}
