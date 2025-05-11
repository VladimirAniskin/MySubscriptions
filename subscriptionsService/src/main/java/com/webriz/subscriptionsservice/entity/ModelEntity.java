package com.webriz.subscriptionsservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Сущность подписки
 */
@Data
@Entity
@Table(name = "model", schema = "subscriptions_service")
@NoArgsConstructor
public class ModelEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "uuid", unique = true)
  private UUID uuid;

  @Column(name = "name")
  private String subscriptionsName;

  @PrePersist
  private void generateUuid() {
    setUuid(UUID.randomUUID());
  }

}
