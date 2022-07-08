package com.kafka.retrytopic.consumer;

public interface IEventConsumerRepository {

    void save(EventConsumer eventConsumer);

    boolean existsByUuidAndNumber(String uuid, String number);
}
