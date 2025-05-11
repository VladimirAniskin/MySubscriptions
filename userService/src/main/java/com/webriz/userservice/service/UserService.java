package com.webriz.userservice.service;

import com.webriz.userservice.dto.UserCreateDto;
import com.webriz.userservice.dto.UserViewDto;
import java.util.List;
import java.util.UUID;

/**
 * Интерфейс сервиса для управления пользователями.
 */
public interface UserService {

  UserViewDto create(UserCreateDto userCreateDto);


  UserViewDto getOne(UUID uuid);

  UserViewDto update(UserCreateDto userCreateDto, UUID uuid);

  void delete(UUID uuid);
}