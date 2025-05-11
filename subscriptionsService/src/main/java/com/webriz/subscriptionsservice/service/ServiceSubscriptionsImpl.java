package com.webriz.subscriptionsservice.service;

import com.webriz.subscriptionsservice.dto.ModelWithCountViewDto;
import com.webriz.subscriptionsservice.dto.SubscriptionsCreateDto;
import com.webriz.subscriptionsservice.dto.SubscriptionsViewDto;
import com.webriz.subscriptionsservice.entity.ModelToSubscriptionsEntity;
import com.webriz.subscriptionsservice.entity.SubscriptionsEntity;
import com.webriz.subscriptionsservice.mapper.ModelMapper;
import com.webriz.subscriptionsservice.mapper.SubscriptionMapper;
import com.webriz.subscriptionsservice.repository.ModelRepository;
import com.webriz.subscriptionsservice.repository.ModelToSubscriptionsRepository;
import com.webriz.subscriptionsservice.repository.SubscriptionRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ServiceSubscriptionsImpl implements ServiceSubscriptions {

  private final SubscriptionMapper subscriptionMapper;
  private final SubscriptionRepository subscriptionRepository;
  private final ModelToSubscriptionsRepository modelToSubscriptionsRepository;
  private final ModelMapper modelMapper;
  private final ModelRepository modelRepository;

  @Override
  @Transactional
  public SubscriptionsViewDto create(SubscriptionsCreateDto dto) {
    try {
      log.info("Creating new subscription for user: {}", dto.getUserUuid());
      var subscriptionEntity = subscriptionMapper.toEntity(dto);
      subscriptionEntity.setCreatedAt(LocalDateTime.now());
      subscriptionEntity = subscriptionRepository.save(subscriptionEntity);
      saveModels(dto, subscriptionEntity);
      log.info("Subscription created successfully with ID: {}", subscriptionEntity.getId());
      return mapToViewDto(subscriptionEntity);
    } catch (Exception e) {
      log.error("Error creating subscription: {}", e.getMessage(), e);
      throw e;
    }
  }

  @Override
  @Transactional
  public void delete(UUID uuid) {
    try {
      log.info("Deleting subscription with UUID: {}", uuid);
      var entity = subscriptionRepository.getByUuid(uuid);
      subscriptionRepository.delete(entity);
      log.info("Subscription deleted successfully");
    } catch (Exception e) {
      log.error("Error deleting subscription: {}", e.getMessage(), e);
      throw e;
    }
  }

  @Override
  @Transactional (readOnly = true)
  public List<SubscriptionsViewDto> getList(UUID userUuid) {
    log.debug("Fetching subscriptions for user: {}", userUuid);
    return subscriptionRepository.findAllByUserUuid(userUuid)
        .stream()
        .map(this::mapToViewDto)
        .toList();
  }
  @Override
  @Transactional (readOnly = true)
  public List<ModelWithCountViewDto> getTop3PopularSubscriptions() {
    log.info("Getting top 3 popular subscriptions");
    return modelToSubscriptionsRepository.findTop3PopularModels();
  }

  private List<ModelWithCountViewDto> getModels(Long subscriptionId) {
    return modelToSubscriptionsRepository.findAllBySubscriptionId(subscriptionId);
  }

  private ModelWithCountViewDto mapToModelWithCountDto(ModelToSubscriptionsEntity entity) {
    var model = modelRepository.findById(entity.getModelId())
        .orElseThrow(() -> new RuntimeException("Model not found"));
    return ModelWithCountViewDto.builder()
        .count(entity.getCount())
        .uuid(model.getUuid())
        .subscriptionsName(model.getSubscriptionsName())
        .build();
  }

  private SubscriptionsViewDto mapToViewDto(SubscriptionsEntity entity) {
    SubscriptionsViewDto dto = subscriptionMapper.toDto(entity);
    dto.setModels(getModels(entity.getId()));
    return dto;
  }

  private void saveModels(SubscriptionsCreateDto dto, SubscriptionsEntity subscriptionEntity) {
    for (var model : dto.getModels()) {
      try {
        var modelEntity = modelRepository.getByUuid(model.getUuid());
        var modelToSubscriptions = ModelToSubscriptionsEntity.builder()
            .subscriptionsEntity(subscriptionEntity)
            .modelEntity(modelEntity)
            .count(model.getCount())
            .build();
        modelToSubscriptionsRepository.save(modelToSubscriptions);
        log.debug("Saved model {} to subscription {}", model.getUuid(), subscriptionEntity.getId());
      } catch (Exception e) {
        log.error("Error saving model to subscription: {}", e.getMessage(), e);
        throw e;
      }
    }
  }
}
