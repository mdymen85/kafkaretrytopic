package com.kafka.retrytopic.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class SubstractService {

    private final IControlKeyRepository controlKeyRepository;

    public void substract(String key) {
        var optControlKey = this.controlKeyRepository.findByKey(key);

        if (!optControlKey.isPresent()) {
            throw new IllegalStateException();
        }

        var controlKey = optControlKey.get();

        if (controlKey.isLastElement()) {
            this.controlKeyRepository.delete(key);
            return;
        }

        controlKey.substract();
        this.controlKeyRepository.save(controlKey);

    }

}
