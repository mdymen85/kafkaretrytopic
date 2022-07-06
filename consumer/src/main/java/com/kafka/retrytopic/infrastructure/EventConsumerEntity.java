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

    public EventConsumerEntity() {

    }

    @Builder
    public EventConsumerEntity(Long id, String uuid, String json) {
        this.id = id;
        this.uuid = uuid;
        this.json = json;
    }
}
