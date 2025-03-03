package org.youcode.EventLinkerAPI.config.chat;


import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.Properties;

@Configuration
public class KafkaTopicConfig {
    private final String bootstrapServers;

    public KafkaTopicConfig(@Value("${KAFKA_BOOTSTRAP_SERVERS}") String bootstrapServers){
        this.bootstrapServers = bootstrapServers;
    }

    @Bean
    public NewTopic directMessagesTopic(){
        return TopicBuilder.name("direct-messages")
                .partitions(5)
                .replicas(1)
                .config(TopicConfig.RETENTION_MS_CONFIG , "604800000")
                .build();
    }

    @Bean
    public AdminClient adminClient(){
        Properties config = new Properties();
        config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG , bootstrapServers);
        config.put(AdminClientConfig.REQUEST_TIMEOUT_MS_CONFIG, "5000");
        return AdminClient.create(config);
    }

}
