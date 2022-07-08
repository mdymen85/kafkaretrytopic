package com.kafka.retrytopic.consumer;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface IRedirectControlService {

    void doRedirect(EventConsumer eventConsumer);
    boolean hasKey(String key);
    void add(String key);
    void saveControlKey(EventConsumer eventConsumer);
    boolean existEvent(EventConsumer eventConsumer);
}
