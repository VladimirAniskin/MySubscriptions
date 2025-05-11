package com.webriz.subscriptionsservice.repository;

import com.webriz.subscriptionsservice.entity.ModelEntity;
import com.webriz.subscriptionsservice.exception.ResponseException;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<ModelEntity,Long> {

  Optional<ModelEntity> findByUuid(UUID uuid);

  default ModelEntity getByUuid(UUID uuid) throws ResponseException {
    return findByUuid(uuid).orElseThrow(ResponseException::new);}
}
