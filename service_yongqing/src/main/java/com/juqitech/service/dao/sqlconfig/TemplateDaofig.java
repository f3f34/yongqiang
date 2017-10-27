package com.juqitech.service.dao.sqlconfig;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(locations = "classpath:db/messageTemplateSql.properties")
public class TemplateDaofig {

    @NotBlank
    private String query_message_template;

    public String getQuery_message_template() {
        return query_message_template;
    }

    public void setQuery_message_template(String query_message_template) {
        this.query_message_template = query_message_template;
    }
}
