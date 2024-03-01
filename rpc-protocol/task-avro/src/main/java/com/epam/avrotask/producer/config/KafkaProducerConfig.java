package com.epam.avrotask.producer.config;

import com.epam.avrotask.entity.MessageEntity;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

import io.confluent.kafka.serializers.KafkaAvroDeserializer;

@Configuration
public class KafkaProducerConfig {

    private static final String SCHEMA_REGISTRY_URL_KEY = "schema.registry.url";
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;
    @Value("${spring.kafka.producer.schema.registry.url}")
    private String registryUrl;

    @Bean
    public ProducerFactory<String, MessageEntity> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class);
        configProps.put(SCHEMA_REGISTRY_URL_KEY, registryUrl);

        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, MessageEntity> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
