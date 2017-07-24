package chinaren.model;

import chinaren.dao.BaseDao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by 李浩然 on 2017/7/24.
 */
@Entity
@Table(name = BaseDao.TABLE_AREA)
public class Area {
    @Column(name = BaseDao.COL_AREA_ID)
    private String areaId;

    @Column(name = BaseDao.COL_AREA)
    private String area;

    @Column(name = BaseDao.COL_FATHER)
    private String father;

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getFather() {
        return father;
    }

    public void setFather(String father) {
        this.father = father;
    }
}
