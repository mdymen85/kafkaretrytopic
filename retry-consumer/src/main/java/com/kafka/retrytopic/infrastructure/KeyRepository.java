package com.kafka.retrytopic.infrastructure;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface KeyRepository extends CrudRepository<ControlKeyEntity, Long> {

    Optional<ControlKeyEntity> findByKey(String key);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE ControlKeyEntity c SET c.count = c.count + 1 WHERE c.key = :key")
    void addKey(@Param("key") String key);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE ControlKeyEntity c SET c.count = c.count - 1 WHERE c.key = :key")
    void substract(String key);

    boolean existsByKey(String key);

    @Modifying
    @Query("delete from ControlKeyEntity c where c.key = :key")
    void deleteByKey(@Param("key") String key);
}
