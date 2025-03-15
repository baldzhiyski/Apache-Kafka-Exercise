package com.baldzhiyski.kafkaexample01;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController  // Marks this class as a Spring MVC Controller and exposes it as a REST API.
@RequestMapping("api/v1/messages")  // Maps the base URL for this controller to 'api/v1/messages'.
public class MessageController {

    // KafkaTemplate is a Spring Kafka class used to send messages to Kafka topics.
    private KafkaTemplate<String, String> kafkaTemplate;

    // Constructor-based injection of KafkaTemplate. Spring will automatically provide the KafkaTemplate instance.
    public MessageController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    // This method listens for POST requests sent to 'api/v1/messages'.
    @PostMapping
    public void publish(@RequestBody MessageRequest messageRequest) {
        // The @RequestBody annotation binds the incoming HTTP request body to the messageRequest object.
        // kafkaTemplate.send() is used to send the message to a Kafka topic (in this case, 'baldzhiyski').
        kafkaTemplate.send("baldzhiyski", messageRequest.message());
        // This will send the 'message' contained in the MessageRequest object to the 'baldzhiyski' Kafka topic.
    }
}

