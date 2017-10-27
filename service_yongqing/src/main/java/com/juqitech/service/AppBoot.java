package com.juqitech.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

import com.juqitech.service.utils.PropertyRepository;
import com.juqitech.service.utils.TicketdashiLog;
import com.juqitech.service.utils.query.result.ResultSetFormatter;

/**
 *
 */
@SpringBootApplication
public class AppBoot extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AppBoot.class);
    }

    public static void main(String[] args) {
        try {
        	PropertyRepository.init("message.properties");
        } catch (Exception e) {
        	TicketdashiLog.error(e.getMessage(), e);
            System.exit(0);
        }
        SpringApplication.run(AppBoot.class, args);
    }
}
