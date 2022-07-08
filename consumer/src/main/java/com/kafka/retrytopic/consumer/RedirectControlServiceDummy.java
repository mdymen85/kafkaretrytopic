package com.kafka.retrytopic.consumer;

import com.kafka.retrytopic.config.retry.IsRetryTopicConsumer;
import org.springframework.stereotype.Component;

@Component
@IsRetryTopicConsumer
public class RedirectControlServiceDummy implements IRedirectControlService {

    @Override
    public boolean existEvent(EventConsumer eventConsumer) {
        return false;
    }

    @Override
    public void doRedirect(EventConsumer eventConsumer) {

    }

    @Override
    public boolean hasKey(String key) {
        return false;
    }

    @Override
    public void add(String key) {

    }

    @Override
    public void saveControlKey(EventConsumer eventConsumer) {

    }
}
