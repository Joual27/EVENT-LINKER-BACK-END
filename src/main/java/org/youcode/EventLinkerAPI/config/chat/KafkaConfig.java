package org.youcode.EventLinkerAPI.config.chat;


import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.youcode.EventLinkerAPI.message.DTOs.MessageResponseDTO;
import org.youcode.EventLinkerAPI.message.Message;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    private final String bootstrapServers;

    public KafkaConfig(@Value("${KAFKA_BOOTSTRAP_SERVERS}") String bootstrapServers){
        this.bootstrapServers = bootstrapServers;
    }

    @Bean
    public ProducerFactory<String , MessageResponseDTO> messageProducerFactory(){
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG , bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG , StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG , JsonSerializer.class);
        configProps.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG , "true");
        configProps.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, true);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public ConsumerFactory<String , MessageResponseDTO> messageConsumerFactory(){
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG , bootstrapServers);
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG , "message-group");
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        configProps.put(JsonDeserializer.VALUE_DEFAULT_TYPE, MessageResponseDTO.class);
        configProps.put(JsonDeserializer.TYPE_MAPPINGS, "messageResponseDTO:org.youcode.EventLinkerAPI.message.DTOs.MessageResponseDTO");
        configProps.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        return new DefaultKafkaConsumerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String , MessageResponseDTO> messageKafkaTemplate(){
        return new KafkaTemplate<>(messageProducerFactory());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String , MessageResponseDTO> messageListenerFactory(){
        ConcurrentKafkaListenerContainerFactory<String , MessageResponseDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(messageConsumerFactory());
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
        return factory;
    }
}
