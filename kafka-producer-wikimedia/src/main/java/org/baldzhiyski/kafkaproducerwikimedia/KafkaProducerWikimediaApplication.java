package org.baldzhiyski.kafkaproducerwikimedia;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaProducerWikimediaApplication implements CommandLineRunner {

    public KafkaProducerWikimediaApplication(WikiMediaChangesProducer wikiMediaChangesProducer) {
        this.wikiMediaChangesProducer = wikiMediaChangesProducer;
    }

    public static void main(String[] args) {
        SpringApplication.run(KafkaProducerWikimediaApplication.class, args);
    }


    private final WikiMediaChangesProducer wikiMediaChangesProducer;
    @Override
    public void run(String... args) throws Exception {
        wikiMediaChangesProducer.sendMessage();
    }
}
