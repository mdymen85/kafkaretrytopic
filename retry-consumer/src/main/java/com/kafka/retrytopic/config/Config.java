package com.kafka.retrytopic.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.retrytopic.consumer.EventConsumer;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
@EnableKafka
@RequiredArgsConstructor
public class Config {

    private final KafkaTemplate<String, EventConsumer> template;

    @Bean
    public ObjectMapper mapper() {
        return new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

//    @Bean
//    public RetryTopicConfiguration myRetryableTopic() {
//        return RetryTopicConfigurationBuilder
//                .newInstance()
//                .maxAttempts(1)
//                .fixedBackOff(1000)
//                .includeTopic("topic-for-reply-retry")
//                .retryOn(Exception.class)
//                .create(template);
//    }

}
