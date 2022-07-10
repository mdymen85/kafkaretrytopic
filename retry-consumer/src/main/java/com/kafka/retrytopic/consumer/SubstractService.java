package com.kafka.retrytopic.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class SubstractService {

    private final IControlKeyRepository controlKeyRepository;

    public void substract(EventConsumer eventConsumer) {
        var optControlKey = this.controlKeyRepository.find(eventConsumer.getUuid(), eventConsumer.getNumber());

        if (!optControlKey.isPresent()) {
            throw new IllegalStateException();
        }

        var controlKey = optControlKey.get();

        if (controlKey.isLastElement()) {
            this.controlKeyRepository.delete(eventConsumer.getUuid());
            return;
        }

        controlKey.substract();
        this.controlKeyRepository.save(controlKey);

    }

}
