package com.kafka.retrytopic.infrastructure;

import com.kafka.retrytopic.consumer.IControlKeyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ControlKeyRepositoryImpl implements IControlKeyRepository {

    private ControlKeyRepository controlKeyRepository;

    @Override
    public void add(String key) {

    }

    @Override
    public void substract(String key) {

    }

    @Override
    public boolean hasKey(String key) {
        return false;
    }

}
