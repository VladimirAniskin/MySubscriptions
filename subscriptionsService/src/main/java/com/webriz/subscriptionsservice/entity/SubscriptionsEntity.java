package com.webriz.subscriptionsservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Сущность связи пользователя с подпиской
 */
@Data
@Entity
@Table(name = "subscriptions", schema = "subscriptions_service")
@NoArgsConstructor
public class SubscriptionsEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "uuid", unique = true)
  private UUID uuid;

  @Column(name = "model_uuid")
  private UUID modelUuid;


  @Column(name = "user_uuid")
  private UUID userUuid;

  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @PrePersist
  private void generateUuid() {
    setUuid(UUID.randomUUID());
  }

}
