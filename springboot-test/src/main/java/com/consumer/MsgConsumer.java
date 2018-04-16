package com.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * 消息消费者
 * @author
 */
@Component
public class MsgConsumer{
	//@KafkaListener(topics = "testlh")
	@KafkaListener(topics = {"testlh","testlh2"})
    public void processMessage(String content) throws Exception{
        System.out.println(content);
    }
}
