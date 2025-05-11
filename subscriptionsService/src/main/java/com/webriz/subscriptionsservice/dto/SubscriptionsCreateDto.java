package com.webriz.subscriptionsservice.dto;


import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;


/**
 * Дто сущности подписки для ввода с фронтэнда
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionsCreateDto {

  private UUID userUuid;

  private List<ModelWithCountCreateDto> models;


}
