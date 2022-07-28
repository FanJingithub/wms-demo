package com.wms.demo.consumer;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.SendResult;
import com.wms.demo.DemoService;

import com.wms.demo.infrastructure.utils.ThreadLocalUtil;
import org.apache.dubbo.config.annotation.DubboReference;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.Date;

@EnableDubbo
@EnableAutoConfiguration
@ComponentScan("com.wms.demo.infrastructure")
public class DubboConsumerBootstrap {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @DubboReference(version = "1.0.0")
    private DemoService demoService;

    @Autowired
    private Producer mqProducer;

    public static void main(String[] args) {
        SpringApplication.run(DubboConsumerBootstrap.class);
    }

    @Bean
    public ApplicationRunner runner() {
        return args -> {
            try {
                ThreadLocalUtil.setTraceId("abcd123456");
                String result = demoService.sayHello("Alibaba-Health WMS");
                logger.info("===================== traceId={}, result: {}", ThreadLocalUtil.getTraceId(), result);

                sendMessage("Hello, Alibaba-Health WMS");
            } catch (Throwable t) {
                logger.error("===================== error={}", t.getMessage());
            }
        };
    }

    private void sendMessage(String msgContent) {
        Message message = new Message("TOPIC_WMS_DEMO", "GID_wms_demo", msgContent.getBytes());
        try {
            SendResult sendResult = mqProducer.send(message);
            // 同步发送消息，只要不抛异常就是成功。
            if (sendResult != null) {
                logger.info("===================== {} Send mq message success. Topic is: {}, msgId is: {}", new Date(), message.getTopic(), sendResult.getMessageId());
            }
        }
        catch (Exception e) {
            // 消息发送失败，需要进行重试处理，可重新发送这条消息或持久化这条数据进行补偿处理。
            logger.error("===================== {} Send mq message success. Topic is: {}, error is: {}", new Date(), message.getTopic(), e.getMessage(), e);
        }

    }
}
