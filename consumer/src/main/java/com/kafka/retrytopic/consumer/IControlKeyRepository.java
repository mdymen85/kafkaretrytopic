package com.kafka.retrytopic.consumer;

public interface IControlKeyRepository {

    void add(String key);
    void substract(String key);
    boolean hasKey(String key);

}
