package com.juqitech.service.service.impl;

import com.juqitech.service.dao.TemplateDao;
import com.juqitech.service.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TemplateServiceImpl implements TemplateService {

    @Autowired
    private TemplateDao templateDao;


    @Override
    public String getTemplate(String type) {
        return null;
    }
}
