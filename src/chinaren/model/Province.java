package chinaren.model;

import chinaren.dao.BaseDao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by 李浩然 on 2017/7/24.
 */
@Entity
@Table(name = BaseDao.TABLE_PROVINCE)
public class Province {
    @Column(name = BaseDao.COL_PROVINCE_ID)
    private String provinceId;

    @Column(name = BaseDao.COL_PROVINCE)
    private String province;

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
