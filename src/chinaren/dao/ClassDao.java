package chinaren.dao;

import java.util.List;

import chinaren.model.Class;
import chinaren.model.Result;

/**
 * 班级数据持久层接口
 * @ClassName ClassDao
 * @author 李浩然
 * @date 2017年7月20日
 * @version 1.0
 */
public interface ClassDao {
	
	/**
	 * 根据班级ID，从数据库中获取班级实体数据
	 * @author 李浩然
	 * @param classId 指定的班级ID
	 * @return 包含一个班级实体的结果实例，若操作失败或不存在相应数据，结果中的班级实体为null
	 */
	public Result<Class> selectClassByClassId(long classId);
	
	/**
	 * 根据班级信息，从数据库中获取班级实体数据（完全匹配）
	 * @author 李浩然
	 * @param province 省份
	 * @param city 城市
	 * @param area 地区
	 * @param school 学校
	 * @param gradeYear 年级
	 * @param className 班级名称
	 * @return 包含一个班级实体的结果实例，若操作失败或不存在相应数据，结果中的班级实体为null
	 */
	public Result<Class> selectClass(
			String province, 
			String city,
			String area,
			String school,
			String gradeYear,
			String className);
	
	/**
	 * 根据班级管理者的用户ID，从数据库中获取班级数据
	 * @author 李浩然
	 * @param managerId 班级管理者的用户ID
	 * @return 包含一个班级实体列表的结果实例，若操作失败或不存在相应数据，结果中的班级列表为空列表
	 */
	public Result<List<Class>> selectClassesByManagerId(long managerId);
	
	/**
	 * 根据班级信息，从数据库中获取所有班级实体数据
	 * @author 李浩然
	 * @return 包含一个班级实体列表的结果实例，若操作失败或不存在相应数据，结果中的班级列表为空列表
	 */
	public Result<List<Class>> selectClasses();
	
	/**
	 * 根据班级信息，从数据库中获取班级实体数据（完全匹配）
	 * @author 李浩然
	 * @param province 省份
	 * @return 包含一个班级实体列表的结果实例，若操作失败或不存在相应数据，结果中的班级列表为空列表
	 */
	public Result<List<Class>> selectClasses(String province);
	
	/**
	 * 根据班级信息，从数据库中获取班级实体数据（完全匹配）
	 * @author 李浩然
	 * @param province 省份
	 * @param city 城市
	 * @return 包含一个班级实体列表的结果实例，若操作失败或不存在相应数据，结果中的班级列表为空列表
	 */
	public Result<List<Class>> selectClasses(String province, String city);
	
	/**
	 * 根据班级信息，从数据库中获取班级实体数据（完全匹配）
	 * @author 李浩然
	 * @param province 省份
	 * @param city 城市
	 * @param area 地区
	 * @return 包含一个班级实体列表的结果实例，若操作失败或不存在相应数据，结果中的班级列表为空列表
	 */
	public Result<List<Class>> selectClasses(
			String province, 
			String city,
			String area);
	
	/**
	 * 根据班级信息，从数据库中获取班级实体数据（模糊查询）
	 * @author 李浩然
	 * @param province 省份
	 * @param city 城市
	 * @param area 地区
	 * @param school 学校
	 * @return 包含一个班级实体列表的结果实例，若操作失败或不存在相应数据，结果中的班级列表为空列表
	 */
	public Result<List<Class>> selectClasses(
			String province, 
			String city,
			String area,
			String school);
	
	/**
	 * 根据班级信息，从数据库中获取班级实体数据（模糊查询）
	 * @author 李浩然
	 * @param province 省份
	 * @param city 城市
	 * @param area 地区
	 * @param school 学校
	 * @param gradeYear 年级
	 * @return 包含一个班级实体列表的结果实例，若操作失败或不存在相应数据，结果中的班级列表为空列表
	 */
	public Result<List<Class>> selectClasses(
			String province, 
			String city,
			String area,
			String school,
			String gradeYear);
	
	/**
	 * 根据班级信息，从数据库中获取班级实体数据（模糊查询）
	 * @author 李浩然
	 * @param province 省份
	 * @param city 城市
	 * @param area 地区
	 * @param school 学校
	 * @param gradeYear 年级
	 * @param className 班级名称
	 * @return 包含一个班级实体列表的结果实例，若操作失败或不存在相应数据，结果中的班级列表为空列表
	 */
	public Result<List<Class>> selectClasses(
			String province, 
			String city,
			String area,
			String school,
			String gradeYear,
			String className);
	
	/**
	 * 向数据库中添加一个班级实体
	 * @author 李浩然
	 * @param clazz 待添加的班级实体
	 * @return 包含一个班级实体的结果实例，若操作失败，结果中的班级实体为null，否则，为新插入的班级实体
	 */
	public Result<Class> insertClass(Class clazz);
	
	/**
	 * 从数据库中删除指定的班级数据
	 * @author 李浩然
	 * @param classId 指定班级的ID
	 * @return 包含Boolean的结果实例
	 */
	public Result<Boolean> deleteClass(long classId);
	
	/**
	 * 更新数据库中指定班级的班级描述
	 * @author 李浩然
	 * @param classId 指定班级的ID
	 * @param description 待替换的新班级描述
	 * @return 包含一个班级实体的结果实例，若操作失败，结果中的班级实体为null，否则，为更新后的班级实体
	 */
	public Result<Class> updateDescription(long classId, String description);
}
