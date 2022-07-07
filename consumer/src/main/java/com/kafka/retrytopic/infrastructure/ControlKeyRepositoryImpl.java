package com.kafka.retrytopic.infrastructure;

import com.kafka.retrytopic.consumer.ControlKey;
import com.kafka.retrytopic.consumer.IControlKeyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class ControlKeyRepositoryImpl implements IControlKeyRepository {

    private final KeyRepository controlKeyRepository;

    @Override
    public void add(String key) {
//        Optional<ControlKeyEntity> optControlKey = controlKeyRepository.findByKey(key);
//        if (optControlKey.isPresent()) {
//            var controlKey = optControlKey.get();
//            controlKey.
//        }
    }

    @Override
    public Optional<ControlKey> findByKey(String key) {
        return Optional.empty();
    }

    @Override
    public boolean hasKey(String key) {
        return false;
    }

    @Override
    public void delete(String key) {

    }

    @Override
    public void save(ControlKey controlKey) {
        var entity = ControlKeyEntity.builder()
                .count(controlKey.getCount())
                .key(controlKey.getKey())
                .build();

        this.controlKeyRepository.save(entity);
    }

}
