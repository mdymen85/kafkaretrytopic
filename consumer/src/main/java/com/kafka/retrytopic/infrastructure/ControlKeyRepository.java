package com.kafka.retrytopic.infrastructure;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ControlKeyRepository extends CrudRepository<ControlKeyEntity, Long> {

    @Query("")
    void add(String key);

}
