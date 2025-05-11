package com.webriz.subscriptionsservice.mapper;

import com.webriz.subscriptionsservice.dto.CreateModelDto;
import com.webriz.subscriptionsservice.dto.ModelDto;
import com.webriz.subscriptionsservice.dto.ModelWithCountViewDto;
import com.webriz.subscriptionsservice.entity.ModelEntity;
import com.webriz.subscriptionsservice.entity.ModelToSubscriptionsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ModelMapper {

  ModelEntity toEntity(CreateModelDto dto);

  ModelDto toDto(ModelEntity modelEntity);

  void update(CreateModelDto modelDto, @MappingTarget ModelEntity modelEntity);

  CreateModelDto toCreateModelDto(ModelEntity modelEntity);

  @Mapping(source = "modelEntity.subscriptionsName", target = "subscriptionsName")
  @Mapping(source = "modelToSubscriptions.count", target = "count")
  @Mapping(source = "modelEntity.uuid", target = "uuid")
  ModelWithCountViewDto toCountDto(ModelToSubscriptionsEntity modelToSubscriptions);
}
