package com.epam.cloudserviceimpl.controller;

import com.epam.jmpdto.entity.dto.SubscriptionRequestDto;
import com.epam.jmpdto.entity.dto.SubscriptionResponseDto;
import com.epam.jmpservice.controller.SubscriptionController;
import com.epam.jmpserviceapi.service.SubscriptionService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name = "Subscription REST API",
    description = "Operations With subscriptions")
@RequestMapping(value = "/api/v1/jmp/subscriptions")
public class SubscriptionControllerImpl implements SubscriptionController {

    private final SubscriptionService subscriptionService;

    @Override
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "The subscription was deleted"),
        @ApiResponse(responseCode = "500", description = "An unexpected error has occurred. The error has been logged and is being investigated.")})
    @Operation(summary = "Delete the subscription by id.")
    @DeleteMapping(value = "/{subscriptionId}")
    public void deleteSubscription(@PathVariable Long subscriptionId) {
        subscriptionService.deleteSubscription(subscriptionId);
    }

    @Override
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "The subscription was received"),
        @ApiResponse(responseCode = "500", description = "An unexpected error has occurred. The error has been logged and is being investigated.")})
    @Operation(summary = "Get the subscription by id.")
    @GetMapping(value = "/{subscriptionId}")
    public SubscriptionResponseDto getSubscription(@PathVariable Long subscriptionId) {
        return subscriptionService.getSubscription(subscriptionId);
    }

    @Override
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "All subscriptions were received"),
        @ApiResponse(responseCode = "500", description = "An unexpected error has occurred. The error has been logged and is being investigated.")})
    @Operation(summary = "Get all subscriptions.")
    @GetMapping
    public List<SubscriptionResponseDto> getAllSubscription() {
        return subscriptionService.getAllSubscription();
    }

    @Override
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "The subscription was updated"),
        @ApiResponse(responseCode = "500", description = "An unexpected error has occurred. The error has been logged and is being investigated.")})
    @Operation(summary = "Update the subscription.")
    @PutMapping
    public SubscriptionResponseDto updateSubscription(@RequestBody SubscriptionRequestDto subscriptionRequestDto) {
        return subscriptionService.updateSubscription(subscriptionRequestDto);
    }

    @Override
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "The subscription was created"),
        @ApiResponse(responseCode = "500", description = "An unexpected error has occurred. The error has been logged and is being investigated.")})
    @Operation(summary = "Create the subscription.")
    @PostMapping
    public SubscriptionResponseDto createSubscription(SubscriptionRequestDto subscriptionRequestDto) {
        return subscriptionService.createSubscription(subscriptionRequestDto);
    }
}