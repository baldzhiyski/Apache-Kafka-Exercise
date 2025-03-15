package com.baldzhiyski.kafkaexample01;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    @KafkaListener(topics = "baldzhiyski", groupId = "new-groupId")
    void listener(String data) {
        System.out.println("Listener received: " + data + " 🎉");
    }

}
