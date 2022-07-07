package com.kafka.retrytopic.producer;

import com.kafka.retrytopic.config.retry.IsRetryTopicConsumerDisabled;
import com.kafka.retrytopic.config.retry.IsRetryTopicConsumerEnabled;
import com.kafka.retrytopic.consumer.EventConsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@IsRetryTopicConsumerDisabled
public class ToProducerDummy implements IToProducer {

    @Override
    public void produce(EventConsumer eventConsumer) {
        log.info("retry producer is disabled.");
    }
}
