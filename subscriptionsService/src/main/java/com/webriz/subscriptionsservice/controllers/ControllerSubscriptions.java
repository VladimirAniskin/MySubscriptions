package com.webriz.subscriptionsservice.controllers;


import com.webriz.subscriptionsservice.dto.ModelWithCountViewDto;
import com.webriz.subscriptionsservice.dto.SubscriptionsCreateDto;
import com.webriz.subscriptionsservice.dto.SubscriptionsViewDto;
import com.webriz.subscriptionsservice.service.ServiceSubscriptions;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequiredArgsConstructor
public class ControllerSubscriptions {

  private final ServiceSubscriptions serviceSubscriptions;

  @PostMapping("/users/{uuid}/subscriptions")
  public ResponseEntity<SubscriptionsViewDto> create(@PathVariable UUID userUuid,
      @RequestBody SubscriptionsCreateDto subscriptionsCreateDto) {
    log.info("Creating subscription for user: {}", userUuid);
    return ResponseEntity.ok(serviceSubscriptions.create(subscriptionsCreateDto));
  }

  @GetMapping("/users/{uuid}/subscriptions")
  public ResponseEntity<List<SubscriptionsViewDto>> getList(UUID userUuid) {
    log.debug("Fetching subscriptions for user: {}", userUuid);
    return ResponseEntity.ok(serviceSubscriptions.getList(userUuid));
  }

  @DeleteMapping("/users/{id}/subscriptions/{sub_id}")
  public ResponseEntity<Void> delete(@PathVariable UUID userUuid,
                                     @PathVariable UUID subscriptionId) {
    log.warn("Deleting subscription {} for user {}", subscriptionId, userUuid);
    serviceSubscriptions.delete(subscriptionId);
    return ResponseEntity.ok().build();
  }
  @GetMapping("/subscriptions/top")
  public ResponseEntity<List<ModelWithCountViewDto>> getTop3PopularSubscriptions() {
    log.info("Getting top 3 popular subscriptions");
    return ResponseEntity.ok(serviceSubscriptions.getTop3PopularSubscriptions());
  }

}
