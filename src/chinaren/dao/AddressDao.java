package chinaren.dao;

import chinaren.model.Area;
import chinaren.model.City;
import chinaren.model.Province;
import chinaren.model.Result;

import java.util.List;

/**
 * Created by 李浩然 on 2017/7/24.
 */
public interface AddressDao {
    public Result<List<Province>> selectProvinces();

    public Result<List<City>> selectCities(String provinceId);

    public Result<List<Area>> selectAreas(String cityId);
}
