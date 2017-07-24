package chinaren.service;

import java.util.List;

import chinaren.model.Message;
import chinaren.model.Result;

/**
 * 留言相关数据处理，服务层接口
 * @ClassName MessageService 
 * @author 李浩然
 * @date 2017年7月21日
 * @version 1.0
 */
public interface MessageService {
	/**
	 * 发布留言服务
	 * @author 李浩然
	 * @param message 待发布的留言
	 * @return 发布结果
	 */
	public Result<Boolean> releaseMessage(Message message);
	
	/**
	 * 删除留言服务
	 * @author 李浩然
	 * @param messageId 待删除留言的ID
	 * @return 删除结果
	 */
	public Result<Boolean> deleteMessage(long messageId);
	
	/**
	 * 获取用户留言列表服务
	 * @author 李浩然
	 * @param userId 用户ID
	 * @return 用户留言列表
	 */
	public Result<List<Message>> getUserMessages(long userId);
	
	/**
	 * 获取班级留言列表服务
	 * @author 李浩然
	 * @param classId 班级ID
	 * @return 班级留言列表
	 */
	public Result<List<Message>> getClassMessages(long classId);
}
