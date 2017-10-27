package com.juqitech.service.config;

import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.juqitech.service.message.MQPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by jms on 2016/3/14.
 */
@Configuration
@EnableTransactionManagement
public class MQPublisherConfig {
    @Primary
    @Bean
    public Producer producer() {
        return ONSFactory.createProducer(getTopicProperties());
    }

    @Bean
    public MQPublisher mqPublisher(Producer producer) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        producer.start();
        return new MQPublisher(producer);
    }

    public Properties getTopicProperties() {
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.ProducerId, System.getProperty("topic_ProducerId")+"_"+System.getProperty("mq_prefix"));
        properties.put(PropertyKeyConst.AccessKey, System.getProperty("topic_AccessKey"));
        properties.put(PropertyKeyConst.SecretKey, System.getProperty("topic_SecretKey"));
        return properties;
    }
}
