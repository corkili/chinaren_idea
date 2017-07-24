package chinaren.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import chinaren.dao.BaseDao;

/**
 * 用户实体类
 * @ClassName: User
 * @author 李浩然
 * @date 2017-7-20
 * @version 1.0
 */
@Entity
@Table(name = BaseDao.TABLE_USER)
public class User implements Serializable {

	private static final long serialVersionUID = 5419632301270768050L;

	/**
	 * 性别类型-男
	 */
	public static final String SEX_MAN = "男";

	/**
	 * 性别类型-女
	 */
	public static final String SEX_WOMAN = "女";

	/**
	 * 用户ID
	 */
	@Column(name = BaseDao.COL_USER_ID)
	private long userId;

	/**
	 * 用户邮箱
	 */
	@Column(name = BaseDao.COL_EMAIL)
	private String email;

	/**
	 * 用户密码哈希值
	 */
	@Column(name = BaseDao.COL_PASSWORD)
	private String password;

	/**
	 * 用户实名
	 */
	@Column(name = BaseDao.COL_NAME)
	private String name;

	/**
	 * 性别
	 */
	@Column(name = BaseDao.COL_SEX)
	private String sex;

	/**
	 * 手机号
	 */
	@Column(name = BaseDao.COL_PHONE)
	private String phone;

	/**
	 * 个人简介
	 */
	@Column(name = BaseDao.COL_INTRODUCTION)
	private String introduction;

	/**
	 * 头像文件路径
	 */
	@Column(name = BaseDao.COL_HEAD_IMAGE)
	private String headImage;

	/**
	 * 所在省份
	 */
	@Column(name = BaseDao.COL_PROVINCE)
	private String province;

	/**
	 * 所在城市
	 */
	@Column(name = BaseDao.COL_CITY)
	private String city;

	/**
	 * 所在地区
	 */
	@Column(name = BaseDao.COL_AREA)
	private String area;

	/**
	 * 用户所属班级的ID列表
	 */
	private List<Long> belongClass;

	/**
	 * 用户申请加入的班级的ID列表
	 */
	private List<Long> applyClass;
	
	/**
	 * 构造函数，初始化belongClass为空列表
	 */
	public User() {
		belongClass = new ArrayList<Long>();
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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the introduction
	 */
	public String getIntroduction() {
		return introduction;
	}

	/**
	 * @param introduction the introduction to set
	 */
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	/**
	 * @return the headImage
	 */
	public String getHeadImage() {
		return headImage;
	}

	/**
	 * @param headImage the headImage to set
	 */
	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	/**
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * @param province the province to set
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the area
	 */
	public String getArea() {
		return area;
	}

	/**
	 * @param area the area to set
	 */
	public void setArea(String area) {
		this.area = area;
	}

	/**
	 * @return the belongClass
	 */
	public List<Long> getBelongClass() {
		return belongClass;
	}

	/**
	 * @param belongClass the belongClass to set
	 */
	public void setBelongClass(List<Long> belongClass) {
		this.belongClass = belongClass;
	}

	/**
	 * @return the applyClass
	 */
	public List<Long> getApplyClass() {
		return applyClass;
	}

	/**
	 * @param applyClass the applyClass to set
	 */
	public void setApplyClass(List<Long> applyClass) {
		this.applyClass = applyClass;
	}
}
