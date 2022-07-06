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

//    private final IControlKeyRepository controlKeyRepository;
//    private final IEventConsumerRepository eventConsumerRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void doRedirect(EventConsumer eventConsumer) {
//        this.controlKeyRepository.add(eventConsumer.getUuid());
//        this.eventConsumerRepository.save(eventConsumer);
    }

    public void doRedirectIfNeeded(EventConsumer eventConsumer) {

    }

}
