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

import chinaren.model.Message;
import chinaren.model.Result;

/**
 * 留言数据持久层实现类
 * @ClassName MessageDaoImpl
 * @author 李浩然
 * @date 2017年7月20日
 * @version 1.0
 */
@Repository
public class MessageDaoImpl extends BaseDao implements MessageDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private Logger logger = Logger.getLogger(MessageDaoImpl.class);

	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss - ");
	
	/**
	 * 构造函数
	 */
	public MessageDaoImpl() {
		
	}

	/**
	 * @see chinaren.dao.MessageDao#selectMessageById(long)
	 */
	@Override
	public Result<Message> selectMessageById(long messageId) {
		logger.info(dateFormat.format(new Date()) + "action: select message by message id");
		String sql = "select * from " + TABLE_MESSAGE + " where " + COL_MESSAGE_ID + "=?";
		logger.info(dateFormat.format(new Date()) + "sql: " + sql);
		Message msg = null;
		boolean successful = false;
		String message = "";
		try {
			RowMapper<Message> rowMapper = BeanPropertyRowMapper.newInstance(Message.class);
			Object[] params = { messageId };
			msg = jdbcTemplate.queryForObject(sql, params, rowMapper);
			successful = msg != null;
			message = successful ? "select<successful>" : "select<failed>";
		} catch (Exception e) {
			successful = false;
			message = "select<failed>";
		}
		logger.info(dateFormat.format(new Date()) + "result: " + message);
		return new Result<Message>(successful, message, msg);
	}

	/**
	 * @see chinaren.dao.MessageDao#selectMessagesByClassId(long)
	 */
	@Override
	public Result<List<Message>> selectMessagesByClassId(long classId) {
		logger.info(dateFormat.format(new Date()) + "action: select messages by class id");
		String sql = "select * from " + TABLE_MESSAGE + " where " + COL_CLASS_ID + "=?";
		logger.info(dateFormat.format(new Date()) + "sql: " + sql);
		List<Message> msgs = null;
		boolean successful = false;
		String message = "";
		try {
			RowMapper<Message> rowMapper = BeanPropertyRowMapper.newInstance(Message.class);
			Object[] params = { classId };
			msgs = jdbcTemplate.query(sql, params, rowMapper);
			msgs = msgs != null ? msgs : new ArrayList<Message>();
			successful = true;
			message = successful ? "select<successful>" : "select<failed>";
		} catch (Exception e) {
			successful = false;
			message = "select<failed>";
			msgs = new ArrayList<Message>();
		}
		logger.info(dateFormat.format(new Date()) + "result: " + message);
		return new Result<List<Message>>(successful, message, msgs);
	}

	/**
	 * @see chinaren.dao.MessageDao#selectMessageByUserId(long)
	 */
	@Override
	public Result<List<Message>> selectMessageByUserId(long userId) {
		logger.info(dateFormat.format(new Date()) + "action: select messages by user id");
		String sql = "select * from " + TABLE_MESSAGE + " where " + COL_USER_ID + "=?";
		logger.info(dateFormat.format(new Date()) + "sql: " + sql);
		List<Message> msgs = null;
		boolean successful = false;
		String message = "";
		try {
			RowMapper<Message> rowMapper = BeanPropertyRowMapper.newInstance(Message.class);
			Object[] params = { userId };
			msgs = jdbcTemplate.query(sql, params, rowMapper);
			msgs = msgs != null ? msgs : new ArrayList<Message>();
			successful = true;
			message = successful ? "select<successful>" : "select<failed>";
		} catch (Exception e) {
			successful = false;
			message = "select<failed>";
			msgs = new ArrayList<Message>();
		}
		logger.info(dateFormat.format(new Date()) + "result: " + message);
		return new Result<List<Message>>(successful, message, msgs);
	}

	/**
	 * @see chinaren.dao.MessageDao#insertMessage(chinaren.model.Message)
	 */
	@Override
	public Result<Message> insertMessage(Message message) {
		logger.info(dateFormat.format(new Date()) + "action: insert a message");
		String sql = "insert into " + TABLE_MESSAGE + " (" + COL_CONTENT + ","
				+ COL_MSG_TIME + "," + COL_USER_ID + "," + COL_CLASS_ID + ") "
				+ "values(?,?,?,?)";
		logger.info(dateFormat.format(new Date()) + "sql: " + sql);
		Message msg = null;
		boolean successful = false;
		String resultMessage = "";
		try {
			Object[] params = { message.getContent(), message.getMsgTime(), 
					message.getUserId(), message.getClassId() };
			successful = jdbcTemplate.update(sql, params) == 1;
			resultMessage = successful ? "insert<successful>" : "insert<failed>";
			if (successful) {
				successful = false;
				for (Message m : selectMessageByUserId(message.getUserId()).getResult()) {
					if (m.getClassId() == message.getClassId()) {
						msg = m;
						successful = true;
						break;
					}
				}
				resultMessage += " and " + (successful ? "select<successful>" : "select<failed>");
			}
		} catch (Exception e) {
			successful = false;
			resultMessage = "insert<failed>";
		}
		logger.info(dateFormat.format(new Date()) + "result: " + message);
		return new Result<Message>(successful, resultMessage, msg);
	}

	/**
	 * @see chinaren.dao.MessageDao#deleteMessage(long)
	 */
	@Override
	public Result<Boolean> deleteMessage(long messageId) {
		logger.info(dateFormat.format(new Date()) + "action: delete message by message id");
		String sql = "delete from " + TABLE_MESSAGE + " where " + COL_MESSAGE_ID + "=?";
		logger.info(dateFormat.format(new Date()) + "sql: " + sql);
		boolean successful = false;
		String message = "";
		try {
			Object[] params = { messageId };
			successful = jdbcTemplate.update(sql, params) == 1;
			message = successful ? "delete<successful>" : "delete<failed>";
		} catch (Exception e) {
			successful = false;
			message = "delete<failed>";
		}
		logger.info(dateFormat.format(new Date()) + "result: " + message);
		return new Result<Boolean>(successful, message, successful);
	}

}
