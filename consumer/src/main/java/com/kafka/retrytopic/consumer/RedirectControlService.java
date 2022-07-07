package com.kafka.retrytopic.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
@RequiredArgsConstructor
public class RedirectControlService {

    private final IControlKeyRepository controlKeyRepository;
    private final IEventConsumerRepository eventConsumerRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void doRedirect(EventConsumer eventConsumer) {
        this.controlKeyRepository.add(eventConsumer.getUuid());
        this.eventConsumerRepository.save(eventConsumer);
    }

    public void substract(String key) {
        var optControlKey = this.controlKeyRepository.findByKey(key);

        if (optControlKey.isPresent()) {
            throw new IllegalStateException();
        }

        var controlKey = optControlKey.get();

        if (controlKey.getCount() == 1) {
            this.controlKeyRepository.delete(key);
            return;
        }

        controlKey.substract();
        this.controlKeyRepository.save(controlKey);

    }

    public boolean hasKey(String key) {
        return controlKeyRepository.hasKey(key);
    }

    public void add(String key) {
        controlKeyRepository.add(key);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveControlKey(EventConsumer eventConsumer) {

        var controlKey = ControlKey.builder()
                .key(eventConsumer.getNumber())
                .count(0)
                .build();

        this.controlKeyRepository.save(controlKey);



    }
}
