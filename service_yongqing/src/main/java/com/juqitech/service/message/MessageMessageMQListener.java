package com.juqitech.service.message;

import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.juqitech.service.message.handler.TemplateHandler;
import com.juqitech.service.message.handler.MessageMessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Map;


@Primary
@Component
public class MessageMessageMQListener extends MQBaseMessageListener {

    @Autowired
    private TemplateHandler templateHandler;


    @Override
    protected void consumeMessage(Message message, ConsumeContext context) throws Exception {
        MessageMessageHandler messageHandler = null;
        String event = message.getKey();
        String msg = new String(message.getBody());

        switch (event) {
            case MessageConstant.evt_bind_weixin_user:
                messageHandler = templateHandler;
                break;

            default:
                break;
        }

        @SuppressWarnings("unchecked")
        Map<String, Object> param = new ObjectMapper().readValue(msg, Map.class);
        if (messageHandler != null) {
            messageHandler.handleMessage(param);
        }
    }
}
