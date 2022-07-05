package com.kafka.retrytopic.consumer;

import com.kafka.retrytopic.producer.IToProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.retrytopic.TopicSuffixingStrategy;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class Consumer {

    @Value("${application.topic.from}")
    private String topic;
    private final IToProducer iToProducer;
    private final IEventConsumerRepository iEventConsumerRepository;

    @RetryableTopic(
            attempts = "3",
            backoff = @Backoff(delay = 1000, multiplier = 2.0))
    @KafkaListener(topics = "${application.topic.from}", groupId = "listener")
    public void consumer(EventConsumer event) throws Exception {
        log.info("Reading message {} from topic {}.", event, topic);

        try {

            throw new Exception();
        }
        catch (Exception e) {
            log.error("error", e);

            iEventConsumerRepository.save(event);

         //   iToProducer.produce(event);

        }

    }

}