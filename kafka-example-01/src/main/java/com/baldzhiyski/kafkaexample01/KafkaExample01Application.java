package com.baldzhiyski.kafkaexample01;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class KafkaExample01Application {

	public static void main(String[] args) {
		SpringApplication.run(KafkaExample01Application.class, args);
	}


	@Bean
	CommandLineRunner commandLineRunner(KafkaTemplate<String, String> kafkaTemplate) {
		return args -> {
			for (int i = 0; i < 10; i++) { // Send fewer messages for testing
				kafkaTemplate.send("baldzhiyski", "Hello Kafka " + i);
				Thread.sleep(500); // Slow down message sending
			}
		};
	}

}
