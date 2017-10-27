package com.juqitech.service.dao;

import com.juqitech.service.utils.query.QueryFilter;

import java.util.List;
import java.util.Map;

public interface TemplateDao {

    List<Map<String,Object>> getCity(QueryFilter filter);
}
