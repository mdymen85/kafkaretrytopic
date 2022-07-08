package com.kafka.retrytopic.infrastructure;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "control_key")
@Data
@Builder
public class ControlKeyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "kafka_key")
    private String key;
    private Integer count;


    public ControlKeyEntity() {

    }

    public ControlKeyEntity(Long id, String key, Integer count) {
        this.id = id;
        this.key = key;
        this.count = count;
    }


}
