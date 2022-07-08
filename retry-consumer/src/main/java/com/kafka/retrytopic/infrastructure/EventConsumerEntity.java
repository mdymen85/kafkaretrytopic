package com.kafka.retrytopic.infrastructure;


import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "event_consumer")
@Data
public class EventConsumerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uuid;

    private String json;

    private String second;

    public EventConsumerEntity() {

    }

    @Builder
    public EventConsumerEntity(Long id, String uuid, String json, String second) {
        this.id = id;
        this.uuid = uuid;
        this.json = json;
        this.second = second;
    }
}
