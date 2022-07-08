package com.kafka.retrytopic.infrastructure;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.retrytopic.consumer.EventConsumer;
import com.kafka.retrytopic.consumer.IEventConsumerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
public class EventConsumerRepositoryImpl implements IEventConsumerRepository {

    private final EventRepository eventRepository;
    private final ObjectMapper mapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void save(EventConsumer eventConsumer) {

        try {

            var entity = EventConsumerEntity.builder()
                    .uuid(eventConsumer.getUuid())
                    .second(eventConsumer.getNumber())
                    .json(mapper.writeValueAsString(eventConsumer))
                    .build();
//
            this.eventRepository.save(entity);
        }
        catch (JsonProcessingException e) {

        }
    }

    @Override
    public boolean existsByUuidAndNumber(String uuid, String number) {
        return eventRepository.existsByUuidAndSecond(uuid, number);
    }

}
