package com.kafka.retrytopic.config.retry;

import com.kafka.retrytopic.consumer.IControlKeyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class RetryTopicConsumerService implements IRetryTopicConsumer {

    private final IControlKeyRepository controlKeyRepository;

    public void substractKey(String key) {
        //this.controlKeyRepository.substract(key);
    }

}
