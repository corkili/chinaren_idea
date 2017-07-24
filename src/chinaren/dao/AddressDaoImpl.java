package chinaren.dao;

import chinaren.model.Area;
import chinaren.model.City;
import chinaren.model.Province;
import chinaren.model.Result;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 李浩然 on 2017/7/24.
 */
@Repository
public class AddressDaoImpl extends BaseDao implements AddressDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Logger logger = Logger.getLogger(AddressDaoImpl.class);

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss - ");

    @Override
    public Result<List<Province>> selectProvinces() {
        logger.info(dateFormat.format(new Date()) + "action: select all provinces");
        String sql = "select * from " + TABLE_PROVINCE;
        logger.info(dateFormat.format(new Date()) + "sql: " + sql);
        List<Province> provinces = null;
        boolean successful = false;
        String message = "";
        try {
            RowMapper<Province> rowMapper = BeanPropertyRowMapper.newInstance(Province.class);
            Object[] params = { };
            provinces = jdbcTemplate.query(sql, params, rowMapper);
            provinces = provinces != null ? provinces : new ArrayList<Province>();
            successful = true;
            message = successful ? "select<successful>" : "select<failed>";
        } catch (Exception e) {
            successful = false;
            message = "select<failed>";
            provinces = new ArrayList<Province>();
        }
        logger.info(dateFormat.format(new Date()) + "result: " + message);
        return new Result<List<Province>>(successful, message, provinces);
    }

    @Override
    public Result<List<City>> selectCities(String provinceId) {
        logger.info(dateFormat.format(new Date()) + "action: select province's cities - province " + provinceId);
        String sql = "select * from " + TABLE_CITY + " where " + COL_FATHER + "=?";
        logger.info(dateFormat.format(new Date()) + "sql: " + sql);
        List<City> cities = null;
        boolean successful = false;
        String message = "";
        try {
            RowMapper<City> rowMapper = BeanPropertyRowMapper.newInstance(City.class);
            Object[] params = { provinceId };
            cities = jdbcTemplate.query(sql, params, rowMapper);
            cities = cities != null ? cities : new ArrayList<City>();
            successful = true;
            message = successful ? "select<successful>" : "select<failed>";
        } catch (Exception e) {
            successful = false;
            message = "select<failed>";
            cities = new ArrayList<City>();
        }
        logger.info(dateFormat.format(new Date()) + "result: " + message);
        return new Result<List<City>>(successful, message, cities);
    }

    @Override
    public Result<List<Area>> selectAreas(String cityId) {
        logger.info(dateFormat.format(new Date()) + "action: select city's areas - city " + cityId);
        String sql = "select * from " + TABLE_AREA + " where " + COL_FATHER + "=?";
        logger.info(dateFormat.format(new Date()) + "sql: " + sql);
        List<Area> areas = null;
        boolean successful = false;
        String message = "";
        try {
            RowMapper<Area> rowMapper = BeanPropertyRowMapper.newInstance(Area.class);
            Object[] params = { cityId };
            areas = jdbcTemplate.query(sql, params, rowMapper);
            areas = areas != null ? areas : new ArrayList<Area>();
            successful = true;
            message = successful ? "select<successful>" : "select<failed>";
        } catch (Exception e) {
            successful = false;
            message = "select<failed>";
            areas = new ArrayList<Area>();
        }
        logger.info(dateFormat.format(new Date()) + "result: " + message);
        return new Result<List<Area>>(successful, message, areas);
    }
}
