/*
 * (c) 2024 EPAM systems, Inc. All Rights Reserved. All information contained herein is, and remains the
 * property of EPAM Systems, Inc. and/or its suppliers and is protected by international intellectual
 * property law. Dissemination of this information or reproduction of this material is strictly forbidden,
 * unless prior written permission is obtained from EPAM Systems, Inc
 */

package com.epam.cloudserviceimpl.mapper;

import com.epam.jmpdto.entity.User;
import com.epam.jmpdto.entity.dto.UserRequestDto;
import com.epam.jmpdto.entity.dto.UserResponseDto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    public static final String DATE_TEMPLATE = "yyyy-MM-dd";

    @Mappings({
        @Mapping(target = "id", source = "id"),
        @Mapping(target = "name", source = "name"),
        @Mapping(target = "surname", source = "surname"),
        @Mapping(target = "birthday", expression = "java(this.getStringDate(user.getBirthday()))")
    })
    public abstract UserResponseDto toUserResponseDto(User user);

    @Mappings({
        @Mapping(target = "id", source = "id"),
        @Mapping(target = "name", source = "name"),
        @Mapping(target = "surname", source = "surname"),
        @Mapping(target = "birthday", expression = "java(this.getLocalDate(userRequestDto.getBirthday()))")
    })
    public abstract User toUser(UserRequestDto userRequestDto);

    @Mappings({
        @Mapping(target = "id", source = "id"),
        @Mapping(target = "name", source = "name"),
        @Mapping(target = "surname", source = "surname"),
        @Mapping(target = "birthday", expression = "java(this.getLocalDate(userResponseDto.getBirthday()))")
    })
    public abstract User toUser(UserResponseDto userResponseDto);

    protected String getStringDate(LocalDate localDate) {
        return localDate.format(DateTimeFormatter.ofPattern(DATE_TEMPLATE));
    }

    protected LocalDate getLocalDate(String localDate) {
        return LocalDate.parse(localDate, DateTimeFormatter.ofPattern(DATE_TEMPLATE));
    }
}