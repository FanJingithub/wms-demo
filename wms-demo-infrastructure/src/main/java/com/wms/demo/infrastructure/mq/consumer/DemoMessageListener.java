package com.wms.demo.infrastructure.mq.consumer;

import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.MessageListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class DemoMessageListener implements MessageListener {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Action consume(Message message, ConsumeContext context) {

        try {
            String content = new String(message.getBody(), StandardCharsets.UTF_8);
            logger.info("=========================== Receive MQ message: {}", message);
            logger.info("=========================== Receive MQ message content: {}", content);
            //do something..
            return Action.CommitMessage;
        } catch (Exception e) {
            //消费失败
            return Action.ReconsumeLater;
        }
    }
}
