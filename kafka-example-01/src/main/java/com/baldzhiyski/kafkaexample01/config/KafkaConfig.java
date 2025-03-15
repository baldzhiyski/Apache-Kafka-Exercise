package com.baldzhiyski.kafkaexample01.config;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

// This configuration class is responsible for creating a Kafka topic programmatically.
// It defines a bean that registers a new topic named "baldzhiyski" using Spring Kafka's TopicBuilder.
// This ensures that the topic is created automatically when the application starts,
// eliminating the need to manually create it in Kafka.
@Configuration
public class KafkaConfig {

    // Method to define and create a new Kafka topic named "baldzhiyski"
    @Bean
    public NewTopic topic() {
        return new NewTopic("baldzhiyski", 1, (short) 1); // partitions=1, replicationFactor=1
    }
}
