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
        var optControlKey = this.controlKeyRepository.findByKey(key);
        if (optControlKey.isPresent()) {
            this.controlKeyRepository.addKey(key);
            return;
        }
        var controlKey = ControlKeyEntity
                .builder()
                .key(key)
                .count(1)
                .build();

        this.controlKeyRepository.save(controlKey);
    }

    @Override
    public Optional<ControlKey> findByKey(String key) {
        var optControlKeyEntity = controlKeyRepository.findByKey(key);

        if (!optControlKeyEntity.isPresent()) {
            return Optional.empty();
        }
        var controlKeyEntity = optControlKeyEntity.get();

        return Optional.of(ControlKey
                .builder()
                .key(controlKeyEntity.getKey())
                .count(controlKeyEntity.getCount())
                .build());
    }

    @Override
    public boolean hasKey(String key) {
        return controlKeyRepository.existsByKey(key);
    }

    @Override
    public void delete(String key) {
        this.controlKeyRepository.deleteByKey(key);
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
