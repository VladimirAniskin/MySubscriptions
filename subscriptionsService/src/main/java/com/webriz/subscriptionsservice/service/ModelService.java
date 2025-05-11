package com.webriz.subscriptionsservice.service;

import com.webriz.subscriptionsservice.dto.CreateModelDto;
import com.webriz.subscriptionsservice.dto.ModelDto;
import java.util.UUID;

/**
 * Сервис для реализации работы с моделями
 */
public interface ModelService {

  ModelDto create(CreateModelDto dto);

  ModelDto find(UUID uuid);


  void delete(UUID uuid);


  CreateModelDto update(UUID uuid, CreateModelDto dto);

}
