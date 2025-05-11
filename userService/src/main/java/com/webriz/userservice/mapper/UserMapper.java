package com.webriz.userservice.mapper;

import com.webriz.userservice.dto.UserCreateDto;
import com.webriz.userservice.dto.UserViewDto;
import com.webriz.userservice.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

/**
 * Интерфейс для преобразования сущности пользователя.
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

  UserEntity toEntity(UserCreateDto userCreateDto);

  UserViewDto toDto(UserEntity userEntity);

  void update(UserCreateDto dto, @MappingTarget UserEntity user);
}
