package com.kafka.retrytopic.consumer;

import java.util.Optional;

public interface IControlKeyRepository {

    void add(String key, String number);
    Optional<ControlKey> find(String key, String number);
    boolean hasKey(String key);
    void delete(String key);
    void save(ControlKey controlKey);
    void substract(ControlKey controlKey);
}
