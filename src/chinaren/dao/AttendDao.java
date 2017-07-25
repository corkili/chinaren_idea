package chinaren.dao;

import java.util.List;

import chinaren.model.Result;

/**
 * 班级-同学关系数据持久层接口
 * @ClassName AttendDao
 * @author 李浩然
 * @date 2017年7月20日
 * @version 1.0
 */
public interface AttendDao {
	
	/**
	 * 根据班级ID，从数据库中获取该班级中所有同学的ID
	 * @author 李浩然
	 * @param classId 指定班级的ID
	 * @param status 审核状态
	 * @return 包含一个用户ID列表的结果实例，若操作失败或不存在相应数据，结果中的列表为空列表
	 */
	public Result<List<Long>> selectUserIdByClassId(long classId, String status);
	
	/**
	 * 根据用户ID，从数据库中获取该用户所在的所有班级的ID
	 * @author 李浩然
	 * @param userId 指定用户的ID
	 * @param status 审核状态
	 * @return 包含一个用户ID列表的结果实例，若操作失败或不存在相应数据，结果中的列表为空列表
	 */
	public Result<List<Long>> selectClassIdByUserId(long userId, String status);
	
	/**
	 * 向数据库中插入一条关系，表示一个用户加入了一个班级
	 * @author 李浩然
	 * @param userId 指定用户的ID
	 * @param classId 指定班级的ID
	 * @return 包含一个Boolean的结果实例
	 */
	public Result<Boolean> insertAttend(long userId, long classId);
	
	/**
	 * 从数据库中删除一条关系，表示一个用户退出会被移出一个班级
	 * @author 李浩然
	 * @param userId 指定用户的ID
	 * @param classId 指定班级的ID
	 * @return 包含一个Boolean的结果实例
	 */
	public Result<Boolean> deleteAttend(long userId, long classId);
	
	/**
	 * 根据班级ID，从数据库中删除该班级中所有的关系
	 * @author 李浩然
	 * @param classId 指定班级的ID
	 * @return 包含一个Boolean的结果实例
	 */
	public Result<Boolean> deleteAttendByClassId(long classId);
	
	/**
	 * 修改数据库中班级-同学关系的状态为True
	 * @author 李浩然
	 * @param userId 要修改的关系的用户ID
	 * @param classId 要修改的关系的班级ID
	 * @return 包含一个Boolean的结果实例
	 */
	public Result<Boolean> updateAttendStatus(long userId, long classId);
}
