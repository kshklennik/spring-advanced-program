package com.epam.avrotask.producer.message;

import com.epam.avrotask.entity.MessageEntity;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.TimeUnit;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@Component
@RequiredArgsConstructor
public class MessageProducer {

    private final KafkaTemplate<String, MessageEntity> kafkaTemplate;

    @SneakyThrows
    public SendResult<String, MessageEntity> sendMessage(String topic, MessageEntity message) {
        ;
        ListenableFuture<SendResult<String, MessageEntity>> send = kafkaTemplate.send(topic, message);
        return send.get(5, TimeUnit.SECONDS);
    }
}