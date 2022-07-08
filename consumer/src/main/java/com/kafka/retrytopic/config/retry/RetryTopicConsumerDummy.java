package com.kafka.retrytopic.config.retry;

import com.kafka.retrytopic.consumer.EventConsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@IsNotRetryTopicConsumer
public class RetryTopicConsumerDummy implements IRetryTopicConsumer {

    public void produce(EventConsumer eventConsumer) {
        log.info("retry producer is disabled.");
    }
}
