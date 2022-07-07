package com.kafka.retrytopic.config.retry;

import com.kafka.retrytopic.consumer.EventConsumer;
import com.kafka.retrytopic.consumer.IControlKeyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@IsRetryTopicConsumerEnabled
@Component
@Slf4j
@RequiredArgsConstructor
public class RetryTopicConsumerService implements IRetryTopicConsumer {

    private final IControlKeyRepository controlKeyRepository;

    public void substractKey(String key) {
        //this.controlKeyRepository.substract(key);
    }

}
