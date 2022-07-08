package com.kafka.retrytopic.producer;

import com.kafka.retrytopic.config.retry.IsNotRetryTopicConsumer;
import com.kafka.retrytopic.consumer.EventConsumer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
@IsNotRetryTopicConsumer
public class ToProducerService implements IToProducer {

    private final KafkaTemplate<String, EventConsumer> template;

    @Value("${application.topic.to}")
    private String retrytopic;

    @Override
    public void produce(EventConsumer eventConsumer) {
        template.send(retrytopic, eventConsumer.getUuid(), eventConsumer);
    }

}
