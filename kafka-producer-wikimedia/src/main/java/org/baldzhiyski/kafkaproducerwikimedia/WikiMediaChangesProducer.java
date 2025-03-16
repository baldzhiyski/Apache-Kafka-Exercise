package org.baldzhiyski.kafkaproducerwikimedia;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;

// Service that initiates the process of connecting to Wikimedia stream and sending data to Kafka
@Service
public class WikiMediaChangesProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(WikiMediaChangesProducer.class);

    // KafkaTemplate used to send messages to Kafka
    private KafkaTemplate<String, String> kafkaTemplate;

    // Constructor to inject KafkaTemplate
    public WikiMediaChangesProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    // Method to connect to Wikimedia stream and send events to Kafka
    public void sendMessage() throws InterruptedException {
        // Define the Kafka topic where event data will be sent
        String topic = "wikimedia_recent_change";

        // Create the event handler that will process incoming events
        EventHandler eventHandler = new WikiMediaChangesHandler(kafkaTemplate, topic);

        // URL of the Wikimedia stream that sends real-time events
        String url = "https://stream.wikimedia.org/v2/stream/recentchange";

        // Build the EventSource to connect to Wikimedia stream
        EventSource.Builder builder = new EventSource.Builder(eventHandler, URI.create(url));

        // Create the EventSource with the handler and URL
        EventSource eventSource = builder.build();

        // Start the EventSource to begin receiving events
        eventSource.start();

        // Wait for 10 minutes to keep the stream open and receive events
        TimeUnit.MINUTES.sleep(10);
    }
}
