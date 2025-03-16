package org.baldzhiyski.kafkaconsumerdatabase.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "wikiData")
@Getter
@Setter
@NoArgsConstructor
public class WikiData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String wikiEventData;

    public WikiData setWikiEventData(String wikiEventData) {
        this.wikiEventData = wikiEventData;
        return this;
    }
}
