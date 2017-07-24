package chinaren.common;

import chinaren.dao.AddressDao;
import chinaren.model.Area;
import chinaren.model.City;
import chinaren.model.Province;
import chinaren.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 李浩然 on 2017/7/24.
 */
public class AddressContext {

    @Autowired
    private AddressDao addressDao;

    private List<Province> provinces;

    private Map<String, List<City>> cities;

    private Map<String, List<Area>> areas;

    private static AddressContext INSTANCE;

    private boolean isLoad;

    /**
     * 私有构造方法
     */
    private AddressContext() {
        cities = new HashMap<>();
        areas = new HashMap<>();
        isLoad = false;
    }

    /**
     * 获取AddressContext实例对象
     * @author 李浩然
     * @return AddressContext单例对象
     */
    public static AddressContext getAddressContext() {
        if (INSTANCE == null) {
            INSTANCE = new AddressContext();
        }
        if (!INSTANCE.isLoad) {
            INSTANCE.load();
        }
        return INSTANCE;
    }

    private void load() {
        // 装载省份
        System.err.println(addressDao);
        Result<List<Province>> provinceResult = addressDao.selectProvinces();
        provinces = provinceResult.getResult();
        // 装载城市和地区
        for (Province province : provinces) {
            Result<List<City>> cityResult = addressDao.selectCities(province.getProvinceId());
            cities.put(province.getProvinceId(), cityResult.getResult());
            for (City city : cityResult.getResult()) {
                Result<List<Area>> areaResult = addressDao.selectAreas(city.getCityId());
                areas.put(city.getCityId(), areaResult.getResult());
            }
        }
    }

    public List<Province> getProvinces() {
        return provinces;
    }

    public Map<String, List<City>> getCities() {
        return cities;
    }

    public Map<String, List<Area>> getAreas() {
        return areas;
    }

}
