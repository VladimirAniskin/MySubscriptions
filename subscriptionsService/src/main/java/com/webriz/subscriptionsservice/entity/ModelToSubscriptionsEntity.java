package com.webriz.subscriptionsservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "model_to_subscriptions", schema = "subscriptions_service")
public class ModelToSubscriptionsEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "model_id")
  private ModelEntity modelEntity;

  @ManyToOne
  @JoinColumn(name = "sub_id")
  private SubscriptionsEntity subscriptionsEntity;

  @Column(name = "model_id", insertable = false, updatable = false)
  private Long modelId;

  @Column(name = "sub_id", insertable = false, updatable = false)
  private Long subscriptionId;

  @Column(name = "count")
  private Long count;
}
