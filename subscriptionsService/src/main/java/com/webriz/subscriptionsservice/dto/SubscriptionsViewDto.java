package com.webriz.subscriptionsservice.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Дто сущности связи подписки
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionsViewDto {

  private UUID uuid;

  private LocalDateTime createdAt;

  private List <ModelWithCountViewDto> models;


}
