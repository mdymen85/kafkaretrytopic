package com.kafka.retrytopic.consumer;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ControlKey {

    private String key;
    private Integer count;

}
