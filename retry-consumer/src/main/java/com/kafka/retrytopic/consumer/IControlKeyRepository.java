package com.kafka.retrytopic.consumer;

import java.util.Optional;

public interface IControlKeyRepository {

    void add(String key);
    Optional<ControlKey> findByKey(String key);
    boolean hasKey(String key);
    void delete(String key);
    void save(ControlKey controlKey);
}
