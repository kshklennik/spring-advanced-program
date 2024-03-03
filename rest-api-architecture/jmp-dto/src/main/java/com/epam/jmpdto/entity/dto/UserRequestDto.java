package com.epam.jmpdto.entity.dto;

import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserRequestDto {

    private Long id;

    @Size(min = 2, message = "Name size must be greater than 2.")
    private String name;

    @Size(min = 2, message = "Surname size must be greater than 2.")
    private String surname;

    private String birthday;
}