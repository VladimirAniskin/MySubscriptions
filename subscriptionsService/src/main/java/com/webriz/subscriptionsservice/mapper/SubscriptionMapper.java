package com.webriz.subscriptionsservice.mapper;

import com.webriz.subscriptionsservice.dto.SubscriptionsCreateDto;
import com.webriz.subscriptionsservice.dto.SubscriptionsViewDto;
import com.webriz.subscriptionsservice.entity.SubscriptionsEntity;
import org.mapstruct.Mapper;

import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {


  SubscriptionsEntity toEntity(SubscriptionsCreateDto dto);

  SubscriptionsViewDto toDto(SubscriptionsEntity entity);

  void update(SubscriptionsCreateDto dto, @MappingTarget SubscriptionsEntity entity);
}
