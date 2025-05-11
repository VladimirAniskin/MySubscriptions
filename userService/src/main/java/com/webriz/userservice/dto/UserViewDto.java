package com.webriz.userservice.dto;

import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Дто сущности пользователя.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserViewDto {


  private UUID uuid;


  private String name;


  private LocalDate registeredAt;


  private String email;
}
