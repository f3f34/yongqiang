package com.juqitech.service.config;

import com.aliyun.openservices.ons.api.Consumer;
import com.aliyun.openservices.ons.api.MessageListener;
import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.juqitech.service.message.MQConsumer;
import com.juqitech.service.message.MQTopic;
import com.juqitech.service.message.MessageConstant;
import com.juqitech.service.utils.TicketdashiLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by jms on 2016/3/11.
 */
@Configuration
@EnableTransactionManagement
public class MQConsumerConfig {
    @Autowired
    private MessageListener listener;
    @Primary
    @Bean
    public Consumer consumer() {
        return ONSFactory.createConsumer(getConsumerProps());
    }

    @Bean
    public MQConsumer mqConsumer(Consumer consumer) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        MQConsumer mqConsumer = new MQConsumer(consumer);
        mqConsumer.subScribeTopic(MQTopic.TOPIC_USER,listener);
//        mqConsumer.subScribeTopic(MessageConstant.evt_bind_weixin_user,listener);
//        mqConsumer.subScribeTopic(MessageConstant.evt_bind_weixin_user_auth,listener);
        mqConsumer.start();
        TicketdashiLog.log("mq start...");
        return  mqConsumer;
    }


    public Properties getConsumerProps(){
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.ConsumerId, System.getProperty("topic_ConsumerId")+"_"+System.getProperty("mq_prefix"));
        properties.put(PropertyKeyConst.AccessKey, System.getProperty("topic_AccessKey"));
        properties.put(PropertyKeyConst.SecretKey, System.getProperty("topic_SecretKey"));
        return properties;
    }
}
