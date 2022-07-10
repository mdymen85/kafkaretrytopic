package com.kafka.retrytopic.consumer;

import com.kafka.retrytopic.config.retry.IsNotRetryTopicConsumer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
@RequiredArgsConstructor
@IsNotRetryTopicConsumer
public class RedirectControlService implements IRedirectControlService {

    private final IControlKeyRepository controlKeyRepository;
    private final IEventConsumerRepository eventConsumerRepository;

    @Override
    public boolean existEvent(EventConsumer eventConsumer) {
        return eventConsumerRepository.existsByUuidAndNumber(eventConsumer.getUuid(), eventConsumer.getNumber());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void doRedirect(EventConsumer eventConsumer) {
        this.controlKeyRepository.add(eventConsumer.getUuid(), eventConsumer.getNumber());
        this.eventConsumerRepository.save(eventConsumer);
    }

    @Override
    public boolean hasKey(String key) {
        return controlKeyRepository.hasKey(key);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void add(String key, String number) {
        controlKeyRepository.add(key, number);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveControlKey(EventConsumer eventConsumer) {

        var controlKey = ControlKey.builder()
                .key(eventConsumer.getNumber())
                .count(0)
                .build();

        this.controlKeyRepository.save(controlKey);

    }
}
