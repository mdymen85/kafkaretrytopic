package com.kakfa.retrytopicproducer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
@Slf4j
public class EventController {

    @Value("${application.topic.to:retryapp-topic}")
    private String topic;

    private final KafkaTemplate<String, EventProducer> kafkaTemplate;

    @RequestMapping(path = "/v1/event", method = RequestMethod.POST)
    public ResponseEntity<EventProducer> post(@RequestBody EventProducer eventConsumer) {

        log.info("Sending message {} to topic {}.", eventConsumer, topic);

        kafkaTemplate.send(topic, eventConsumer);

        return new ResponseEntity<EventProducer>(eventConsumer, HttpStatus.ACCEPTED);

    }

}
