package com.baldzhiyski.kafkaexample01.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.util.HashMap;
import java.util.Map;

/**
 * KafkaConsumerConfig sets up the configuration for consuming messages from a Kafka topic.
 * It defines the properties required for deserializing messages, creates a consumer factory,
 * and provides a Kafka listener container factory for managing message consumption in a concurrent environment.
 * This configuration ensures that Kafka consumers can listen to topics and process messages efficiently.
 */
@Configuration
public class KafkaConsumerConfig {

    // Injects the Kafka broker address from the application properties.
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    /**
     * Configures the Kafka consumer properties, specifying the bootstrap server,
     * key and value deserializers, and other necessary settings.
     *
     * @return A map of properties required for the Kafka consumer.
     */
    private Map<String, Object> consumerConfig() {
        HashMap<String, Object> props = new HashMap<>();

        // Kafka broker address
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);

        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return props;
    }

    /**
     * Creates and returns a Kafka ConsumerFactory, which is responsible
     * for creating new Kafka consumer instances.
     *
     * @return A configured ConsumerFactory instance.
     */
    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfig());
    }

    /**
     * Creates and configures a KafkaListenerContainerFactory,
     * which manages concurrent Kafka listeners.
     *
     * @param consumerFactory The ConsumerFactory used to create consumers.
     * @return A configured KafkaListenerContainerFactory for handling Kafka messages.
     */
    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> factory(
            ConsumerFactory<String, String> consumerFactory) {

        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();

        // Sets the consumer factory for the listener container
        factory.setConsumerFactory(consumerFactory);

        return factory;
    }
}
