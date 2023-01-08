package io.poststead.poststeadauditservice.repository;

import io.poststead.poststeadauditservice.subscriber.model.EventInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventInfoRepository extends JpaRepository<EventInfoEntity, UUID> {
}
