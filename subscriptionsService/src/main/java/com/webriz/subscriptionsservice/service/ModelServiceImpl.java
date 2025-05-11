package com.webriz.subscriptionsservice.service;

import com.webriz.subscriptionsservice.dto.CreateModelDto;
import com.webriz.subscriptionsservice.dto.ModelDto;
import com.webriz.subscriptionsservice.mapper.ModelMapper;
import com.webriz.subscriptionsservice.repository.ModelRepository;
import jakarta.transaction.Transactional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ModelServiceImpl implements ModelService {

  private final ModelMapper modelMapper;
  private final ModelRepository modelRepository;

  @Override
  @Transactional
  public ModelDto create(CreateModelDto dto) {
    try {
      var entity = modelMapper.toEntity(dto);
      entity = modelRepository.save(entity);
      log.info("Model created successfully with ID: {}", entity.getId());
      return modelMapper.toDto(entity);
    } catch (Exception e) {
      log.error("Error creating model: {}", e.getMessage(), e);
      throw e;
    }
  }

  @Override
  @Transactional
  public ModelDto find(UUID uuid) {
    log.debug("Fetching model with UUID: {}", uuid);
    var entity = modelRepository.getByUuid(uuid);
    return modelMapper.toDto(entity);
  }

  @Override
  @Transactional
  public CreateModelDto update(UUID uuid, CreateModelDto dto) {
    try {
      var entity = modelRepository.getByUuid(uuid);
      modelMapper.update(dto, entity);
      entity = modelRepository.save(entity);
      log.info("Model updated successfully with UUID: {}", uuid);
      return modelMapper.toCreateModelDto(entity);
    } catch (Exception e) {
      log.error("Error updating model: {}", e.getMessage(), e);
      throw e;
    }
  }

  @Override
  @Transactional
  public void delete(UUID uuid) {
    try {
      var entity = modelRepository.getByUuid(uuid);
      modelRepository.delete(entity);
      log.info("Model deleted successfully with UUID: {}", uuid);
    } catch (Exception e) {
      log.error("Error deleting model: {}", e.getMessage(), e);
      throw e;
    }
  }
}