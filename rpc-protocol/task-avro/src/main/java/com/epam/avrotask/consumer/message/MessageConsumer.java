package com.epam.avrotask.consumer.message;

import com.epam.avrotask.entity.MessageEntity;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MessageConsumer {

    @KafkaListener(topics = "${spring.kafka.topicName}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(MessageEntity message) {
        log.info("Received message: {}", message);
    }
}
