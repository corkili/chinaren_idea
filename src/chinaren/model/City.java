package chinaren.model;

import chinaren.dao.BaseDao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by 李浩然 on 2017/7/24.
 */
@Entity
@Table(name = BaseDao.TABLE_CITY)
public class City {
    @Column(name = BaseDao.COL_CITY_ID)
    private String cityId;

    @Column(name = BaseDao.COL_CITY)
    private String city;

    @Column(name = BaseDao.COL_FATHER)
    private String father;

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getFather() {
        return father;
    }

    public void setFather(String father) {
        this.father = father;
    }
}
