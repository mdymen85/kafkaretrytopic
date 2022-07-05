package com.kafka.retrytopic.infrastructure;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventConsumerRepository extends CrudRepository<EventConsumerEntity, Long> {
}
