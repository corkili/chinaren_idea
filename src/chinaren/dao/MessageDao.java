package chinaren.dao;

import java.util.List;

import chinaren.model.Message;
import chinaren.model.Result;

/**
 * 留言数据持久层接口
 * @ClassName MessageDao
 * @author 李浩然
 * @date 2017年7月20日
 * @version 1.0
 */
public interface MessageDao {
	
	/**
	 * 根据留言ID，从数据库中获取留言数据
	 * @author 李浩然
	 * @param messageId 指定的留言ID
	 * @return 包含一个留言实体的结果实例，若操作失败或不存在相应数据，结果中的留言实体为null
	 */
	public Result<Message> selectMessageById(long messageId);
	
	/**
	 * 从数据库中获取属于该班级的所有留言数据
	 * @author 李浩然
	 * @param classId 指定班级的ID
	 * @return 包含一个留言实体列表的结果实例，若操作失败或不存在相应数据，结果中的留言实体列表为空列表
	 */
	public Result<List<Message>> selectMessagesByClassId(long classId);
	
	/**
	 * 从数据库中获取一个用户发布的所有留言数据
	 * @author 李浩然
	 * @param userId 指定用户的ID
	 * @return 包含一个留言实体列表的结果实例，若操作失败或不存在相应数据，结果中的留言实体列表为空列表
	 */
	public Result<List<Message>> selectMessageByUserId(long userId);
	
	/**
	 * 向数据库中添加一条新的留言数据
	 * @author 李浩然
	 * @param message 待添加的留言
	 * @return 包含一个留言实体的结果实例，若操作失败，结果中的留言实体为null，否则为新添加的留言实体
	 */
	public Result<Message> insertMessage(Message message);
	
	/**
	 * 从数据库中删除一条的留言数据
	 * @author 李浩然
	 * @param messageId 待删除留言的ID
	 * @return 包含一个Boolean的结果实例
	 */
	public Result<Boolean> deleteMessage(long messageId);

}
