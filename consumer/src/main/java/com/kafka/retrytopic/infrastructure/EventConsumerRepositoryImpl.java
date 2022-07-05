package com.kafka.retrytopic.infrastructure;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.retrytopic.consumer.EventConsumer;
import com.kafka.retrytopic.consumer.IEventConsumerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
public class EventConsumerRepositoryImpl implements IEventConsumerRepository {

    private final EventConsumerRepository eventConsumerRepository;
    private final ObjectMapper mapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void save(EventConsumer eventConsumer) throws JsonProcessingException {

        var entity = EventConsumerEntity.builder()
                .json(mapper.writeValueAsString(eventConsumer))
                .build();

        this.eventConsumerRepository.save(entity);
    }
}
