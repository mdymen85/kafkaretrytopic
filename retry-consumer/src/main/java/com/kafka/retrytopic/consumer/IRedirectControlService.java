package com.kafka.retrytopic.consumer;

public interface IRedirectControlService {

    void doRedirect(EventConsumer eventConsumer);
    boolean hasKey(String key);
    void add(String key);
    void saveControlKey(EventConsumer eventConsumer);

}
