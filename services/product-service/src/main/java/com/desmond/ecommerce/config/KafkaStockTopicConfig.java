package com.desmond.ecommerce.config;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaStockTopicConfig {
    @Bean
    public NewTopic orderTopic() {
        return TopicBuilder
                .name("stock-topic")
                .build();
    }
}
