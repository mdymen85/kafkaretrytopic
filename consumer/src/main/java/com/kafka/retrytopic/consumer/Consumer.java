package com.kafka.retrytopic.consumer;

import com.kafka.retrytopic.producer.IToProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class Consumer {

    @Value("${application.topic.from}")
    private String topic;
    private final IToProducer iToProducer;

//    @RetryableTopic(
//            attempts = "4",
//            backoff = @Backoff(delay = 1000, multiplier = 2.0),
//            topicSuffixingStrategy = TopicSuffixingStrategy.SUFFIX_WITH_INDEX_VALUE)
    @KafkaListener(topics = "${application.topic.from}", groupId = "listener")
    public void consumer(EventConsumer event) throws Exception {
        log.info("Reading message {} from topic {}.", event, topic);

        try {

            throw new Exception();
        }
        catch (Exception e) {
            log.error("error",e);

            iToProducer.produce(event);

        }

    }

}