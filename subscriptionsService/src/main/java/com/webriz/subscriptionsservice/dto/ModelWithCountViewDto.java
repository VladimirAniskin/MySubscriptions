package com.webriz.subscriptionsservice.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ModelWithCountViewDto {
  private Long count;
  private UUID uuid;
  private String subscriptionsName;
}
