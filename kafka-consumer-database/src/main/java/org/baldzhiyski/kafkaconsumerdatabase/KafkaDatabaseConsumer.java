package org.baldzhiyski.kafkaconsumerdatabase;

import org.baldzhiyski.kafkaconsumerdatabase.entity.WikiData;
import org.baldzhiyski.kafkaconsumerdatabase.repository.WikiDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaDatabaseConsumer {

    private WikiDataRepository wikiDataRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaDatabaseConsumer.class);

    public KafkaDatabaseConsumer(WikiDataRepository wikiDataRepository) {
        this.wikiDataRepository = wikiDataRepository;
    }

    @KafkaListener(
            topics = "wikimedia_recent_change",
            groupId = "my-group"
    )
    public void consume(String eventMessage){
        LOGGER.info(String.format("Event Message received -> %s",eventMessage));

        WikiData wikiData = new WikiData();

        wikiData.setWikiEventData(eventMessage);

        this.wikiDataRepository.save(wikiData);
    }

}
