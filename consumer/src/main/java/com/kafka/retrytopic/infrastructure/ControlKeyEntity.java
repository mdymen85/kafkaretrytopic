package com.kafka.retrytopic.infrastructure;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "event_consumer_control_key")
@Data
public class ControlKeyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String key;
    private Integer count;


    public ControlKeyEntity() {

    }
}
