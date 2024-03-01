package com.epam.avrotask.deserializer;

import com.epam.avrotask.entity.MessageEntity;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.apache.kafka.common.serialization.Deserializer;

import java.io.Serializable;

import lombok.SneakyThrows;

public class MessageDeserializer implements Serializable, Deserializer<MessageEntity> {

    @SneakyThrows
    @Override
    public MessageEntity deserialize(String topic, byte[] data) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        return objectMapper.readValue(data, MessageEntity.class);
    }
}
