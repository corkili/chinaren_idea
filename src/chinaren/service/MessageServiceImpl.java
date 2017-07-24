package chinaren.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import chinaren.dao.MessageDao;
import chinaren.model.Message;
import chinaren.model.Result;

/**
 * 留言相关数据处理，服务层实现类
 * @ClassName MessageServiceImpl 
 * @author 李浩然
 * @date 2017年7月21日
 * @version 1.0
 */
@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageDao messageDao;
	
	private Logger logger = Logger.getLogger(MessageServiceImpl.class);
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss - ");
	
	/**
	 * 构造方法
	 */
	public MessageServiceImpl() {
		
	}

	/**
	 * @see chinaren.service.MessageService#releaseMessage(chinaren.model.Message)
	 */
	@Override
	public Result<Boolean> releaseMessage(Message message) {
		logger.info(dateFormat.format(new Date()) + "release message");
		
		if (message == null
				|| message.getContent() == null
				|| message.getUserId() <= 0
				|| message.getClassId() <= 0) {
			logger.info(dateFormat.format(new Date()) + "release message: invalid");
			return new Result<Boolean>(false, "留言数据非法，请重试！", false);
		}
		
		Result<Message> result = messageDao.insertMessage(message);
		if (!result.isSuccessful()) {
			logger.info(dateFormat.format(new Date()) + "release message: failed");
			return new Result<Boolean>(false, "数据库错误，请重试！", false);
		}
		logger.info(dateFormat.format(new Date()) + "release message: successful");
		return new Result<Boolean>(true, "留言发布成功", true);
	}

	/**
	 * @see chinaren.service.MessageService#deleteMessage(long)
	 */
	@Override
	public Result<Boolean> deleteMessage(long messageId) {
		logger.info(dateFormat.format(new Date()) + "delete message - message " + messageId);
		Result<Boolean> result = messageDao.deleteMessage(messageId);
		if (!result.isSuccessful()) {
			logger.info(dateFormat.format(new Date()) + "delete message: failed - message " + messageId);
			return new Result<Boolean>(false, "删除留言时发生错误", false);
		}
		logger.info(dateFormat.format(new Date()) + "delete message: successful - message " + messageId);
		return new Result<Boolean>(true, "留言删除成功", true);
	}

	/**
	 * @see chinaren.service.MessageService#getUserMessages(long)
	 */
	@Override
	public Result<List<Message>> getUserMessages(long userId) {
		logger.info(dateFormat.format(new Date()) + "get user's message lis - user " + userId);
		return messageDao.selectMessageByUserId(userId);
	}

	/**
	 * @see chinaren.service.MessageService#getClassMessages(long)
	 */
	@Override
	public Result<List<Message>> getClassMessages(long classId) {
		logger.info(dateFormat.format(new Date()) + "get class's message lis - class " + classId);
		return messageDao.selectMessagesByClassId(classId);
	}

}
