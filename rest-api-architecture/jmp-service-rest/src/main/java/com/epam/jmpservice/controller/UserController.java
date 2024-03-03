package com.epam.jmpservice.controller;

import com.epam.jmpdto.entity.dto.UserRequestDto;
import com.epam.jmpdto.entity.dto.UserResponseDto;

import java.util.List;

public interface UserController {

    void deleteUser(Long userId);

    UserResponseDto getUser(Long userId);

    List<UserResponseDto> getAllUser();

    UserResponseDto updateUser(UserRequestDto userRequestDto);

    UserResponseDto createUser(UserRequestDto userRequestDto);
}