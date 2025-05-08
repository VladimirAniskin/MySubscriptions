package com.webriz.userservice.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Дто сущности пользователя для ввода с фронтэнда
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserCreateDto {
  @NotBlank
  private String name;
  @Email
  private String email;
}
