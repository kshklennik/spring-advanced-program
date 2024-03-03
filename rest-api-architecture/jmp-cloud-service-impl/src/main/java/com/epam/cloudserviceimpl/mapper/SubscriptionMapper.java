/*
 * (c) 2024 EPAM systems, Inc. All Rights Reserved. All information contained herein is, and remains the
 * property of EPAM Systems, Inc. and/or its suppliers and is protected by international intellectual
 * property law. Dissemination of this information or reproduction of this material is strictly forbidden,
 * unless prior written permission is obtained from EPAM Systems, Inc
 */

package com.epam.cloudserviceimpl.mapper;

import com.epam.jmpdto.entity.Subscription;
import com.epam.jmpdto.entity.dto.SubscriptionResponseDto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Mapper(componentModel = "spring")
public abstract class SubscriptionMapper {

    public static final String DATE_TEMPLATE = "yyyy-MM-dd";

    @Mappings({
        @Mapping(target = "id", source = "id"),
        @Mapping(target = "userId", expression = "java(subscription.getUser().getId())"),
        @Mapping(target = "startDate", expression = "java(this.getStringDate(subscription.getStartDate()))")
    })
    public abstract SubscriptionResponseDto toSubscriptionResponseDto(Subscription subscription);

    protected String getStringDate(LocalDate localDate) {
        return localDate.format(DateTimeFormatter.ofPattern(DATE_TEMPLATE));
    }

}