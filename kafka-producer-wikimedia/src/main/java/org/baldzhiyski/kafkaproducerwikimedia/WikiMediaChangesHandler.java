package org.baldzhiyski.kafkaproducerwikimedia;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.MessageEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;

// Event handler that processes events from Wikimedia stream and sends them to Kafka
public class WikiMediaChangesHandler implements EventHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(WikiMediaChangesHandler.class);

    // KafkaTemplate used to send messages to Kafka
    private KafkaTemplate<String, String> kafkaTemplate;

    // The Kafka topic to which data will be sent
    private String topic;

    // Constructor to inject KafkaTemplate and topic name
    public WikiMediaChangesHandler(KafkaTemplate<String, String> kafkaTemplate, String topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    // Called when the event stream connection opens
    @Override
    public void onOpen() throws Exception {
        // You can add logic here if you want something to happen when the connection opens
    }

    // Called when the event stream connection is closed
    @Override
    public void onClosed() throws Exception {
        // You can add logic here if you want something to happen when the connection closes
    }

    // Called whenever a new message event is received from the Wikimedia stream
    @Override
    public void onMessage(String s, MessageEvent messageEvent) throws Exception {
        // Log the event data received
        LOGGER.info(String.format("event data -> %s", messageEvent.getData()));

        // Send the event data to Kafka topic
        kafkaTemplate.send(topic, messageEvent.getData());
    }

    // Called when a comment is received in the event stream (not used here)
    @Override
    public void onComment(String s) throws Exception {
        // You can handle comments here if you need
    }

    // Called when an error occurs in the event stream
    @Override
    public void onError(Throwable throwable) {
        // Handle error (you might want to log or report it)
        LOGGER.error("Error occurred in event stream", throwable);
    }
}
