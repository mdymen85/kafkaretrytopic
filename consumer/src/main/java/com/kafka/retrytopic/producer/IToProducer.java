package com.kafka.retrytopic.producer;

import com.kafka.retrytopic.consumer.EventConsumer;

public interface IToProducer {

    void produce(EventConsumer eventConsumer);

}
