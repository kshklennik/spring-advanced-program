package com.epam.avrotask.deserializer;

import com.epam.avrotask.entity.MessageEntity;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.kafka.common.serialization.Deserializer;

import java.io.Serializable;

import lombok.SneakyThrows;

public class MessageDeserializer implements Serializable, Deserializer<MessageEntity> {

    @SneakyThrows
    @Override
    public MessageEntity deserialize(String topic, byte[] data) {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(data, MessageEntity.class);
    }
}
