package chinaren.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import chinaren.dao.BaseDao;

/**
 * 留言实体类
 * @ClassName Message
 * @author 李浩然
 * @date 2017年7月20日
 * @version 1.0
 */
@Entity
@Table(name = BaseDao.TABLE_MESSAGE)
public class Message implements Serializable {

	private static final long serialVersionUID = -3394925740684560555L;

	/**
	 * 留言ID
	 */
	@Column(name = BaseDao.COL_MESSAGE_ID)
	private long messageId;

	/**
	 * 留言内容
	 */
	@Column(name = BaseDao.COL_CONTENT)
	private String content;

	/**
	 * 留言时间
	 */
	@Column(name = BaseDao.COL_MSG_TIME)
	private Date msgTime;

	/**
	 * 发布留言的用户的ID
	 */
	@Column(name = BaseDao.COL_USER_ID)
	private long userId;

	/**
	 * 留言所属班级的ID
	 */
	@Column(name = BaseDao.COL_CLASS_ID)
	private long classId;

	/**
	 * @return the messageId
	 */
	public long getMessageId() {
		return messageId;
	}

	/**
	 * @param messageId the messageId to set
	 */
	public void setMessageId(long messageId) {
		this.messageId = messageId;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the msgTime
	 */
	public Date getMsgTime() {
		return msgTime;
	}

	/**
	 * @param msgTime the msgTime to set
	 */
	public void setMsgTime(Date msgTime) {
		this.msgTime = msgTime;
	}

	/**
	 * @return the userId
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * @return the classId
	 */
	public long getClassId() {
		return classId;
	}

	/**
	 * @param classId the classId to set
	 */
	public void setClassId(long classId) {
		this.classId = classId;
	}
}
