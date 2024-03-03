package com.epam.jmpserviceapi.service;

import com.epam.jmpdto.entity.dto.SubscriptionRequestDto;
import com.epam.jmpdto.entity.dto.SubscriptionResponseDto;

import java.util.List;

public interface SubscriptionService {

    void deleteSubscription(Long subscriptionId);

    SubscriptionResponseDto getSubscription(Long subscriptionId);

    List<SubscriptionResponseDto> getAllSubscription();

    SubscriptionResponseDto updateSubscription(SubscriptionRequestDto subscriptionRequestDto);

    SubscriptionResponseDto createSubscription(SubscriptionRequestDto subscriptionRequestDto);
}
