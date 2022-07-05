package com.kafka.retrytopic.infrastructure;


import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "event_consumer")
@Data
@Builder
public class EventConsumerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uuid;

    private String json;

    public EventConsumerEntity() {

    }
}
