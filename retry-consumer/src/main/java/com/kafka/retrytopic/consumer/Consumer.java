package com.kafka.retrytopic.consumer;

import com.kafka.retrytopic.consumer.aspects.Redirect;
import com.kafka.retrytopic.consumer.aspects.Substract;
import com.kafka.retrytopic.producer.IToProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
@Transactional
public class Consumer {

    @Value("${application.topic.from}")
    private String topic;
    private final IToProducer iToProducer;
    private final IRedirectControlService redirectControlService;

    @Redirect
    @Substract
    @RetryableTopic(
            attempts = "3",
            backoff = @Backoff(delay = 1000, multiplier = 2.0))
    @KafkaListener(topics = "${application.topic.from}", groupId = "listener")
    public void consumer(EventConsumer eventConsumer) throws Exception {
        log.info("Reading message {} from topic {}.", eventConsumer, topic);

        try {

            log.info("consume {}", eventConsumer);

        }
        catch (Exception e) {
            log.error("error", e);

            redirectControlService.doRedirect(eventConsumer);

            throw new Exception();


        }

    }

}