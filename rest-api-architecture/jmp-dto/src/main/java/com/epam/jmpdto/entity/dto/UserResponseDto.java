package com.epam.jmpdto.entity.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDto {

    private Long id;
    private String name;
    private String surname;
    private String birthday;
}