package com.epam.jmpdto.entity.dto;

import lombok.Data;

@Data
public class SubscriptionResponseDto {

    private Long id;
    private Long userId;
    private String startDate;
}