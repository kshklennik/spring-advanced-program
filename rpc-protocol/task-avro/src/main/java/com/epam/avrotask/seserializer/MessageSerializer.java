package com.epam.avrotask.seserializer;

import com.epam.avrotask.entity.MessageEntity;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.apache.kafka.common.serialization.Serializer;

import java.io.Serializable;

import lombok.SneakyThrows;

public class MessageSerializer implements Serializable, Serializer<MessageEntity> {

    @Override
    @SneakyThrows
    public byte[] serialize(String topic, MessageEntity data) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        return objectMapper.writeValueAsBytes(data);
    }
}
