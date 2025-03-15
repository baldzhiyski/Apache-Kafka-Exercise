package com.baldzhiyski.kafkaexample01.config;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic baldzhiyskiTopic(){
        return TopicBuilder.name("baldzhiyski")
                .build();
    }
}
