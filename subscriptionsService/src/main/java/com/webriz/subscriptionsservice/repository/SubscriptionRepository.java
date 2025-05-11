package com.webriz.subscriptionsservice.repository;
import com.webriz.subscriptionsservice.exception.ResponseException;
import com.webriz.subscriptionsservice.entity.SubscriptionsEntity;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SubscriptionRepository extends JpaRepository<SubscriptionsEntity, Long> {

  @Query("SELECT s FROM SubscriptionsEntity s WHERE s.uuid = :uuid")
  Optional<SubscriptionsEntity> findByUuid(@Param("uuid") UUID uuid);

  @Query("SELECT s FROM SubscriptionsEntity s WHERE s.userUuid = :userUuid")
  List<SubscriptionsEntity> findAllByUserUuid(@Param("userUuid") UUID userUuid);

  default SubscriptionsEntity getByUuid(UUID uuid) {
    return findByUuid(uuid).orElseThrow();
  }
}
