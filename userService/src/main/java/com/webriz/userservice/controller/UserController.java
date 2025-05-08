package com.webriz.userservice.controller;



import static com.webriz.userservice.config.ApiConfig.USERS;

import com.webriz.userservice.dto.UserCreateDto;
import com.webriz.userservice.dto.UserViewDto;
import com.webriz.userservice.entity.UserEntity;
import com.webriz.userservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для сущности пользователей {@link UserEntity}
 */

@RestController
@RequiredArgsConstructor
@RequestMapping(USERS)
public class UserController {

  private final UserService service;
  @Operation(summary = "Создание пользователя")
  @PostMapping()
  public ResponseEntity<UserViewDto> create(@Valid @RequestBody UserCreateDto dto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
  }


  @Operation(summary = "Получение пользователя по UUID")
  @GetMapping("/{uuid}")
  public ResponseEntity<UserViewDto> getOne(
      @PathVariable
      @Parameter(description = "Глобальный идентификатор пользователя", required = true)
      UUID uuid) {
    return ResponseEntity.ok(service.getOne(uuid));
  }

  @Operation(summary = "Обновление пользователя по UUID")
  @PutMapping("/{uuid}")
  public ResponseEntity<UserViewDto> update(
      @Valid @RequestBody UserCreateDto userCreateDto,
      @PathVariable
      @Parameter(description = "Глобальный идентификатор пользователя", required = true)
      UUID uuid) {
    return ResponseEntity.ok(service.update(userCreateDto, uuid));
  }

  @Operation(summary = "Удаление пользователя по UUID")
  @DeleteMapping("/{uuid}")
  public ResponseEntity<Void> delete(
      @PathVariable
      @Parameter(description = "Глобальный идентификатор пользователя", required = true)
      UUID uuid) {
    service.delete(uuid);
    return ResponseEntity.noContent().build();
  }
}