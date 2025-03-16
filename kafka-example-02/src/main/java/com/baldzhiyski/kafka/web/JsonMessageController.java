package com.baldzhiyski.kafka.web;

import com.baldzhiyski.kafka.kafka.KafkaProducerJson;
import com.baldzhiyski.kafka.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/kafka")
public class JsonMessageController {


    private KafkaProducerJson kafkaProducerJson;

    public JsonMessageController(KafkaProducerJson kafkaProducerJson) {
        this.kafkaProducerJson = kafkaProducerJson;
    }

    @PostMapping("/publish")
    public ResponseEntity<String> getUser(@RequestBody User user){
        kafkaProducerJson.sendMessage(user);
        return ResponseEntity.ok("Json Message successfully send to kafka topic");
    }
}
