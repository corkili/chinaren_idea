package chinaren.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import chinaren.dao.BaseDao;

/**
 * 班级实体类
 * @ClassName Class
 * @author 李浩然
 * @date 2017年7月20日
 * @version 1.0
 */
@Entity
@Table(name = BaseDao.TABLE_CLASS)
public class Class implements Serializable{

	private static final long serialVersionUID = -4499276679953479446L;
	
	/**
	 * 班级ID
	 */
	@Column(name = BaseDao.COL_CLASS_ID)
	private long classId;

	/**
	 * 班级所在学校
	 */
	@Column(name = BaseDao.COL_SCHOOL)
	private String school;

	/**
	 * 班级名称
	 */
	@Column(name = BaseDao.COL_CLASS_NAME)
	private String className;

	/**
	 * 班级年份
	 */
	@Column(name = BaseDao.COL_GRADE_YEAR)
	private String gradeYear;

	/**
	 * 班级描述
	 */
	@Column(name = BaseDao.COL_DESCRIPTION)
	private String decription;

	/**
	 * 班级所在省份
	 */
	@Column(name = BaseDao.COL_PROVINCE)
	private String province;

	/**
	 * 班级所在城市
	 */
	@Column(name = BaseDao.COL_CITY)
	private String city;

	/**
	 * 班级所在地区
	 */
	@Column(name = BaseDao.COL_AREA)
	private String area;

	/**
	 * 班级管理者ID
	 */
	@Column(name = BaseDao.COL_MANAGER_ID)
	private long managerId;
	
	/**
	 * 班级中所有同学的ID列表
	 */
	private List<Long> classmates;
	
	/**
	 * 申请加入班级的用户的ID列表
	 */
	private List<Long> notApplys;
	
	/**
	 * 构造函数，初始化classmates为空列表
	 */
	public Class() {
		classmates = new ArrayList<Long>();
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

	/**
	 * @return the school
	 */
	public String getSchool() {
		return school;
	}

	/**
	 * @param school the school to set
	 */
	public void setSchool(String school) {
		this.school = school;
	}

	/**
	 * @return the className
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * @param className the className to set
	 */
	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 * @return the gradeYear
	 */
	public String getGradeYear() {
		return gradeYear;
	}

	/**
	 * @param gradeYear the gradeYear to set
	 */
	public void setGradeYear(String gradeYear) {
		this.gradeYear = gradeYear;
	}

	/**
	 * @return the decription
	 */
	public String getDecription() {
		return decription;
	}

	/**
	 * @param decription the decription to set
	 */
	public void setDecription(String decription) {
		this.decription = decription;
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
	 * @return the managerId
	 */
	public long getManagerId() {
		return managerId;
	}

	/**
	 * @param managerId the managerId to set
	 */
	public void setManagerId(long managerId) {
		this.managerId = managerId;
	}

	/**
	 * @return the classmates
	 */
	public List<Long> getClassmates() {
		return classmates;
	}

	/**
	 * @param classmates the classmates to set
	 */
	public void setClassmates(List<Long> classmates) {
		this.classmates = classmates;
	}

	/**
	 * @return the notApplys
	 */
	public List<Long> getNotApplys() {
		return notApplys;
	}

	/**
	 * @param notApplys the notApplys to set
	 */
	public void setNotApplys(List<Long> notApplys) {
		this.notApplys = notApplys;
	}
	
	/**
	 * 判断用户是否在班级中
	 * @author 李浩然
	 * @param userId 待判断用户的ID
	 * @return 若在班级中，返回true；否则，返回false
	 */
	public boolean contain(long userId) {
		for (Long id : classmates) {
			if (id == userId) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 判断用户是否为班级管理者
	 * @author 李浩然
	 * @param userId 待判断用户的ID
	 * @return 若是管理者，返回true；否则，返回false
	 */
	public boolean isManager(long userId) {
		return userId == this.managerId;
	}
}

