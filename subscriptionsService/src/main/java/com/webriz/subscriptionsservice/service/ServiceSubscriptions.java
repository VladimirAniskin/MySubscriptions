package com.webriz.subscriptionsservice.service;

import com.webriz.subscriptionsservice.dto.ModelWithCountViewDto;
import com.webriz.subscriptionsservice.dto.SubscriptionsCreateDto;
import com.webriz.subscriptionsservice.dto.SubscriptionsViewDto;
import java.util.List;
import java.util.UUID;


public interface ServiceSubscriptions {

  SubscriptionsViewDto create(SubscriptionsCreateDto subscriptionsCreateDto);

  List<SubscriptionsViewDto> getList(UUID userUuid);

  void delete(UUID uuid);
  List<ModelWithCountViewDto> getTop3PopularSubscriptions();
}
