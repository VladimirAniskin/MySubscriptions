package com.webriz.subscriptionsservice.controllers;

import com.webriz.subscriptionsservice.dto.CreateModelDto;
import com.webriz.subscriptionsservice.dto.ModelDto;
import com.webriz.subscriptionsservice.service.ModelService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ControllerModel {

  private final ModelService modelService;

  @PostMapping("/model/create")
  public ModelDto create(@RequestBody CreateModelDto dto) {
    log.info("Creating new model with name: {}", dto.getSubscriptionsName());
    return modelService.create(dto);
  }

  @GetMapping("/model/{uuid}")
  public ModelDto find(@PathVariable UUID uuid) {
    log.debug("Fetching model with UUID: {}", uuid);
    return modelService.find(uuid);
  }

  @PutMapping("/{uuid}")
  public CreateModelDto update(@PathVariable UUID uuid, @RequestBody CreateModelDto dto) {
    log.info("Updating model with UUID: {}", uuid);
    return modelService.update(uuid, dto);
  }

  @DeleteMapping("/{uuid}")
  public ResponseEntity <Void> delete1(@PathVariable UUID uuid) {
    log.warn("Deleting model with UUID: {}", uuid);
    modelService.delete(uuid);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
