package com.juqitech.service.dao.imp;

import com.juqitech.service.dao.TemplateDao;
import com.juqitech.service.dao.sqlconfig.TemplateDaofig;
import com.juqitech.service.db.SqlBase;
import com.juqitech.service.utils.query.QueryFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;



@Repository
public class TemplateDaoImpl extends SqlBase implements TemplateDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private TemplateDaofig daofig;

//    @Override
//    public boolean addCity(String cityOID,String province, String city) {
//        Object[] args = new Object[]{
//                cityOID,
//                province,
//                city
//        };
//        return addExecute(daofig.getCreate_cite(),args);
//    }
//
//    @Override
//    public List<Map<String, Object>> getCity(QueryFilter filter) {
//        return queryList(daofig.getGet_city(),null,filter);
//    }
//
//    @Override
//    public List<Map<String, Object>> getIPCity(QueryFilter filter) {
//        return queryList(daofig.getGet_ip_city(),null,filter);
//    }
//
//    @Override
//    public boolean addIPCity(String ip, String cityOID) {
//        Object[] args = new Object[]{
//                ip,
//                cityOID
//        };
//        return addExecute(daofig.getCreate_ip_city(),args);
//    }
//
//    @Override
//    public boolean addUserIPCity(String ip, String cityOID, String userOID) {
//        Object[] args = new Object[]{
//                ip,
//                userOID,
//                cityOID
//        };
//        return addExecute(daofig.getCreate_ip_city_user(),args);
//    }
//
//    @Override
//    public Integer getCityMaxId(String province) {
//        Map<String,Object> map =  jdbcTemplate.queryForMap(daofig.getGet_city_maxId(),province);
//        return (Integer) map.get("cityOID");
//    }
//
//

    @Override
    public String getClassName() {
        return getClass().getName();
    }

    @Override
    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    @Override
    public List<Map<String, Object>> getCity(QueryFilter filter) {
        return queryList(daofig.getQuery_message_template(),null,filter);
    }

}
