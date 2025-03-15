package com.baldzhiyski.kafkaexample01.config;

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

@Configuration
// This configuration class sets up a Kafka producer for sending messages to Kafka topics.
// It defines the necessary Kafka producer configuration (e.g., bootstrap servers and serializers),
// creates a ProducerFactory that handles the creation of Kafka producers, and provides a KafkaTemplate
// to send messages. The KafkaTemplate is an abstraction that simplifies message production to Kafka.
// These components are configured using Spring's dependency injection to ensure smooth integration with
// the Kafka producer in the application.
public class KafkaProducerConfig {

    // This property holds the Kafka bootstrap servers address from the application's configuration (application.properties or application.yml)
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    // Method to define Kafka producer configuration properties
    private Map<String, Object> producerConfig() {
        // Create a new HashMap to store Kafka configuration settings
        HashMap<String, Object> props = new HashMap<>();

        // Set the bootstrap server property (Kafka brokers to connect to)
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);

        // Set the serializer for the key (how to convert the message key into bytes)
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        // Set the serializer for the value (how to convert the message value into bytes)
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        // Return the configuration map
        return props;
    }

    // Method to create and return a ProducerFactory which is responsible for creating Kafka producers
    @Bean
    public ProducerFactory<String, String> producerFactory() {
        // Return a new DefaultKafkaProducerFactory with the provided producer configuration
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }

    // Method to create and return a KafkaTemplate, which simplifies sending messages to Kafka
    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String, String> producerFactory) {
        // Create and return a KafkaTemplate using the ProducerFactory defined above
        return new KafkaTemplate<>(producerFactory);
    }
}
