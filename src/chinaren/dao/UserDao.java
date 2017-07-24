package chinaren.dao;

import java.util.List;

import chinaren.model.Result;
import chinaren.model.User;

/**
 * 用户数据持久层接口
 * @ClassName UserDao
 * @author 李浩然
 * @date 2017年7月20日
 * @version 1.0
 */
public interface UserDao {
	
	/**
	 * 根据用户ID，从数据库中获取用户实体数据
	 * @author 李浩然
	 * @param userId 指定的用户ID
	 * @return 包含一个用户实体的结果实例，若操作失败或不存在相应数据，结果中的用户实体为null
	 */
	public Result<User> selectUserByUserId(long userId);
	
	/**
	 * 根据用户Email，从数据库中获取用户实体数据
	 * @author 李浩然
	 * @param email 指定的用户email
	 * @return 包含一个用户实体的结果实例，若操作失败或不存在相应数据，结果中的用户实体为null
	 */
	public Result<User> selectUserByEmail(String email);
	
	/**
	 * 根据班级ID，从数据库中获取班级中所有用户的实体数据
	 * @author 李浩然
	 * @param classId 指定的班级ID
	 * @return 包含一个用户实体列表的结果实例，若操作失败或不存在相应数据，结果中的用户实体列表为空列表
	 */
	public Result<List<User>> selectUsersByClassId(long classId);
	
	/**
	 * 向数据库中插入一条新的用户数据
	 * @author 李浩然
	 * @param user 待插入的用户实体
	 * @return 包含一个用户实体的结果实例，该实例为插入成功的用户数据，若操作失败，结果中的用户实体为null
	 */
	public Result<User> insertUser(User user);
	
	/**
	 * 更新数据库中指定用户的密码
	 * @author 李浩然
	 * @param userId 指定的用户ID
	 * @param password 新密码
	 * @return 包含一个用户实体的结果实例，该实例为更新后的用户实体，若操作失败，结果中的用户实体为null
	 */
	public Result<User> updatePassword(long userId, String password);
	
	/**
	 * 更新数据库中指定用户的信息（不包括userId, email, password, headImage）
	 * @author 李浩然
	 * @param user 待更新信息的用户实体
	 * @return 包含一个用户实体的结果实例，该实例为更新后的用户实体，若操作失败，结果中的用户实体为null
	 */
	public Result<User> updateUserInfo(User user);
	
}
