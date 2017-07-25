package chinaren.dao;

/**
 * 数据持久层基类，包含了各种公用的字段
 * @ClassName BaseDao
 * @author 李浩然
 * @date 2017年7月20日
 * @version 1.0
 */
public abstract class BaseDao {
	
	/**
	 * 审核已通过的状态
	 */
	public static final String STATUS_TRUE = "T";
	
	/**
	 * 尚未审核的状态；若审核不通过，则不存在相应记录
	 */
	public static final String STATUS_FALSE = "F";
	
	/**
	 * 省份表名
	 */
	public static final String TABLE_PROVINCE = "province";
	
	/**
	 * 城市表名
	 */
	public static final String TABLE_CITY = "city";
	
	/**
	 * 地区表名
	 */
	public static final String TABLE_AREA = "area";
	
	/**
	 * 用户表名
	 */
	public static final String TABLE_USER = "user";
	
	/**
	 * 班级表名
	 */
	public static final String TABLE_CLASS = "class";
	
	/**
	 * 班级-同学关系表名
	 */
	public static final String TABLE_ATTEND = "attend";
	
	/**
	 * 留言表名
	 */
	public static final String TABLE_MESSAGE = "message";
	
	/**
	 * 省份ID字段名
	 */
	public static final String COL_PROVINCE_ID = "provinceId";
	
	/**
	 * 省份名称字段名
	 */
	public static final String COL_PROVINCE = "province";
	
	/**
	 * 城市ID字段名
	 */
	public static final String COL_CITY_ID = "cityId";
	
	/**
	 * 城市名称字段名
	 */
	public static final String COL_CITY = "city";
	
	/**
	 * 城市所属省份的ID或地区所属城市的ID字段名
	 */
	public static final String COL_FATHER = "father";

	/**
	 * 地区ID字段名
	 */
	public static final String COL_AREA_ID = "areaId";
	
	/**
	 * 地区名称字段名
	 */
	public static final String COL_AREA = "area";
	
	/**
	 * 用户ID字段名
	 */
	public static final String COL_USER_ID = "userId";
	
	/**
	 * 用户邮箱字段名
	 */
	public static final String COL_EMAIL = "email";
	
	/**
	 * 用户密码字段名
	 */
	public static final String COL_PASSWORD = "password";
	
	/**
	 * 用户实名字段名
	 */
	public static final String COL_NAME = "name";
	
	/**
	 * 性别字段名
	 */
	public static final String COL_SEX = "sex";
	
	/**
	 * 手机号字段名
	 */
	public static final String COL_PHONE = "phone";
	
	/**
	 * 个人简介字段名
	 */
	public static final String COL_INTRODUCTION = "introduction";
	
	/**
	 * 头像字段名
	 */
	public static final String COL_HEAD_IMAGE = "headImage";
	
	/**
	 * 班级ID字段名
	 */
	public static final String COL_CLASS_ID = "classId";
	
	/**
	 * 学校字段名
	 */
	public static final String COL_SCHOOL = "school";
	
	/**
	 * 班级名称字段名
	 */
	public static final String COL_CLASS_NAME = "className";
	
	/**
	 * 班级年份字段名
	 */
	public static final String COL_GRADE_YEAR = "gradeYear";
	
	/**
	 * 班级简介字段名
	 */
	public static final String COL_DESCRIPTION = "description";
	
	/**
	 * 班级管理者ID字段名
	 */
	public static final String COL_MANAGER_ID = "managerId";

	/**
	 * 班级管理者姓名
	 */
	public static final String COL_MANAGER_NAME = "managerName";
	
	/**
	 * 班级-同学关系ID字段名
	 */
	public static final String COL_ATTEND_ID = "attendId";
	

	/**
	 * 班级-同学关系审核状态字段名
	 */
	public static final String COL_STATUS = "status";
	
	
	/**
	 * 留言ID字段名
	 */
	public static final String COL_MESSAGE_ID = "messageId";
	
	/**
	 * 留言内容字段名
	 */
	public static final String COL_CONTENT = "content";
	
	/**
	 * 留言时间字段名
	 */
	public static final String COL_MSG_TIME = "msgTime";
}
