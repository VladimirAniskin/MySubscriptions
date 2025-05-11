package com.webriz.subscriptionsservice.dto;

import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class ModelWithCountCreateDto {
  private UUID uuid;

  private Long count;
}
