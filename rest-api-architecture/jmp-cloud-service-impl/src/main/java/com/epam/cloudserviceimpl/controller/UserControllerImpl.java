package com.epam.cloudserviceimpl.controller;

import com.epam.jmpdto.entity.dto.UserRequestDto;
import com.epam.jmpdto.entity.dto.UserResponseDto;
import com.epam.jmpservice.controller.UserController;
import com.epam.jmpserviceapi.service.UserService;

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
@Tag(name = "User REST API",
    description = "Operations With users")
@RequestMapping(value = "/api/v1/jmp/users")
public class UserControllerImpl implements UserController {

    private final UserService userService;

    @Override
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "The user was deleted"),
        @ApiResponse(responseCode = "500", description = "An unexpected error has occurred. The error has been logged and is being investigated.")})
    @Operation(summary = "Delete the user by id.")
    @DeleteMapping(value = "/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }

    @Override
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "The user was received"),
        @ApiResponse(responseCode = "500", description = "An unexpected error has occurred. The error has been logged and is being investigated.")})
    @Operation(summary = "Get the user by id.")
    @GetMapping(value = "/{userId}")
    public UserResponseDto getUser(@PathVariable Long userId) {
        return userService.getUser(userId);
    }

    @Override
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "All users were received"),
        @ApiResponse(responseCode = "500", description = "An unexpected error has occurred. The error has been logged and is being investigated.")})
    @Operation(summary = "Get all users.")
    @GetMapping
    public List<UserResponseDto> getAllUser() {
        return userService.getAllUser();
    }

    @Override
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "The user was updated"),
        @ApiResponse(responseCode = "500", description = "An unexpected error has occurred. The error has been logged and is being investigated.")})
    @Operation(summary = "Update the user.")
    @PutMapping
    public UserResponseDto updateUser(@RequestBody UserRequestDto userRequestDto) {
        return userService.updateUser(userRequestDto);
    }

    @Override
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "The user was created"),
        @ApiResponse(responseCode = "500", description = "An unexpected error has occurred. The error has been logged and is being investigated.")})
    @Operation(summary = "Create the user.")
    @PostMapping
    public UserResponseDto createUser(@RequestBody UserRequestDto userRequestDto) {
        return userService.createUser(userRequestDto);
    }
}