package com.webriz.subscriptionsservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 *  Дто сущности модели для ввода с фронтэнда
 *
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateModelDto {

  private String subscriptionsName;
}
