package com.webriz.subscriptionsservice.repository;
import com.webriz.subscriptionsservice.dto.ModelWithCountViewDto;
import java.util.List;
import com.webriz.subscriptionsservice.entity.ModelToSubscriptionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ModelToSubscriptionsRepository extends JpaRepository <ModelToSubscriptionsEntity,Long> {
  @Query("SELECT new com.webriz.subscriptionsservice.dto.ModelWithCountViewDto("
      + "m.uuid, m.subscriptionsName, COUNT(mts.id)) " +
      "FROM ModelToSubscriptionsEntity mts " +
      "JOIN mts.modelEntity m " +
      "GROUP BY m.uuid, m.subscriptionsName " +
      "ORDER BY COUNT(mts.id) DESC LIMIT 3")
  List<ModelWithCountViewDto> findTop3PopularModels();

  @Query("SELECT new com.webriz.subscriptionsservice.dto.ModelWithCountViewDto(m.uuid, m.subscriptionsName, mts.count) " +
      "FROM ModelToSubscriptionsEntity mts " +
      "JOIN mts.modelEntity m " +
      "WHERE mts.subscriptionsEntity.id = :subscriptionId")
  List<ModelWithCountViewDto> findAllBySubscriptionId(@Param("subscriptionId") Long subscriptionId);
}

