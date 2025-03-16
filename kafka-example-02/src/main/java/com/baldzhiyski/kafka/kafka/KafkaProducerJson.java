package com.baldzhiyski.kafka.kafka;


import com.baldzhiyski.kafka.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerJson {


    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducerJson.class);


    private KafkaTemplate<String, User> kafkaTemplate;

    public KafkaProducerJson(KafkaTemplate<String, User> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    public void sendMessage(User data){

        LOGGER.info(String.format("Message sent ->  %s" ,data.toString()));
        Message<User> message = MessageBuilder.withPayload(data)
                .setHeader(KafkaHeaders.TOPIC,"javaguides_json")
                .build();

        kafkaTemplate.send(message);
    }
}
