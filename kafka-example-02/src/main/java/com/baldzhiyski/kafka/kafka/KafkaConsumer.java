package com.baldzhiyski.kafka.kafka;

import com.baldzhiyski.kafka.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "javaguides",groupId = "my-group")
    public void listen(String message){

        LOGGER.info(String.format("Message received %s" ,message));

    }

    @KafkaListener(topics = "javaguides_json", groupId = "my-group")
    public void listen(User user) {
        LOGGER.info("Received User: " + user);
    }
}
