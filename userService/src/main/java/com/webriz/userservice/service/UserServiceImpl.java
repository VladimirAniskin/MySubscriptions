package com.webriz.userservice.service;

import com.webriz.userservice.dto.UserCreateDto;
import com.webriz.userservice.dto.UserViewDto;
import com.webriz.userservice.mapper.UserMapper;
import com.webriz.userservice.repository.UserRepository;
import java.time.LocalDate;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository repository;
  private final UserMapper mapper;

  @Override
  @Transactional
  public UserViewDto create(UserCreateDto dto) {
    log.info("Создание нового пользователя с email: {}", dto.getEmail());
    var entity = mapper.toEntity(dto);
    entity.setRegisteredAt(LocalDate.now());

    try {
      entity = repository.save(entity);
      log.debug("Пользователь успешно создан с ID: {}", entity.getId());
      return mapper.toDto(entity);
    } catch (Exception e) {
      log.error("Ошибка при создании пользователя: {}", e.getMessage(), e);
      throw e;
    }
  }

  @Override
  @Transactional(readOnly = true)
  public UserViewDto getOne(UUID uuid) {
    log.debug("Получение пользователя с UUID: {}", uuid);
    var entity = repository.getUser(uuid);
    log.info("Найден пользователь: {} (UUID: {})", entity.getEmail(), uuid);
    return mapper.toDto(entity);
  }

  @Override
  @Transactional
  public UserViewDto update(UserCreateDto dto, UUID uuid) {
    log.info("Обновление пользователя с UUID: {}", uuid);
    var entity = repository.getUser(uuid);
    mapper.update(dto, entity);

    try {
      entity = repository.save(entity);
      log.debug("Пользователь с UUID {} успешно обновлен", uuid);
      return mapper.toDto(entity);
    } catch (Exception e) {
      log.error("Ошибка при обновлении пользователя: {}", e.getMessage(), e);
      throw e;
    }
  }

  @Override
  @Transactional
  public void delete(UUID uuid) {
    log.warn("Удаление пользователя с UUID: {}", uuid);
    try {
      repository.delete(repository.getUser(uuid));
      log.info("Пользователь с UUID {} успешно удален", uuid);
    } catch (Exception e) {
      log.error("Ошибка при удалении пользователя: {}", e.getMessage(), e);
      throw e;
    }
  }
}