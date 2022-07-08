package com.kafka.retrytopic.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface IEventConsumerRepository {

    void save(EventConsumer eventConsumer);

}
