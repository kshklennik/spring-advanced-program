package com.epam.avrotask.controller;

import com.epam.avrotask.entity.MessageEntity;
import com.epam.avrotask.producer.message.MessageProducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class KafkaController {

    @Value("${spring.kafka.topicName}")
    private String topicName;

    @Autowired
    private MessageProducer messageProducer;

    @PostMapping("/send")
    public String sendMessage(@RequestBody MessageEntity message) {
        SendResult<String, MessageEntity> result =
            messageProducer.sendMessage(topicName, message);

        log.info("Message sent: {}", message);
        return result.getProducerRecord().key();
    }
}