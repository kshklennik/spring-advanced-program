package com.epam.cloudserviceimpl.service;

import com.epam.cloudserviceimpl.exception.SubscriptionNotFoundException;
import com.epam.cloudserviceimpl.mapper.SubscriptionMapper;
import com.epam.jmpdto.entity.Subscription;
import com.epam.jmpdto.entity.User;
import com.epam.jmpdto.entity.dto.SubscriptionRequestDto;
import com.epam.jmpdto.entity.dto.SubscriptionResponseDto;
import com.epam.jmpdto.repository.SubscriptionRepository;
import com.epam.jmpserviceapi.service.SubscriptionService;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final UserServiceExtended userService;
    private final SubscriptionMapper subscriptionMapper;

    @Override
    public void deleteSubscription(Long subscriptionId) {
        //check
        getSubscriptionById(subscriptionId);

        log.info("Deleting sSubscription. ID: {}", subscriptionId);
        subscriptionRepository.deleteById(subscriptionId);
    }

    @Override
    public SubscriptionResponseDto getSubscription(Long subscriptionId) {
        Subscription Subscription = getSubscriptionById(subscriptionId);
        return subscriptionMapper.toSubscriptionResponseDto(Subscription);
    }

    @Override
    public List<SubscriptionResponseDto> getAllSubscription() {
        log.info("Getting all Subscriptions");

        return StreamSupport.stream(subscriptionRepository.findAll().spliterator(), false)
            .map(subscriptionMapper::toSubscriptionResponseDto)
            .collect(Collectors.toList());
    }

    @Override
    public SubscriptionResponseDto updateSubscription(SubscriptionRequestDto subscriptionRequestDto) {
        log.info("Updating Subscription. ID: {}", subscriptionRequestDto.getId());

        Subscription subscriptionToUpdate = getSubscriptionById(subscriptionRequestDto.getId());
        User user = userService.getUserById(subscriptionRequestDto.getUserId());

        subscriptionToUpdate.setUser(user);
        subscriptionToUpdate.setStartDate(LocalDate.now());

        Subscription updatedSubscription = subscriptionRepository.save(subscriptionToUpdate);
        return subscriptionMapper.toSubscriptionResponseDto(updatedSubscription);
    }

    @Override
    public SubscriptionResponseDto createSubscription(SubscriptionRequestDto subscriptionRequestDto) {
        log.info("Creating a new Subscription. User: {}", subscriptionRequestDto.getUserId());
        User user = userService.getUserById(subscriptionRequestDto.getUserId());

        Subscription savedSubscription = subscriptionRepository.save(
            Subscription.builder()
            .startDate(LocalDate.now())
            .user(user)
            .build());

        return subscriptionMapper.toSubscriptionResponseDto(savedSubscription);
    }

    private Subscription getSubscriptionById(Long SubscriptionId) {
        log.info("Getting Subscription. ID: {}", SubscriptionId);
        return subscriptionRepository.findById(SubscriptionId)
            .orElseThrow(() -> new SubscriptionNotFoundException("Subscription not found. id: {}" + SubscriptionId));
    }
}
