package com.kafka.retrytopic.infrastructure;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface KeyRepository extends CrudRepository<ControlKeyEntity, Long> {

    Optional<ControlKeyEntity> findByKey(String key);
}
