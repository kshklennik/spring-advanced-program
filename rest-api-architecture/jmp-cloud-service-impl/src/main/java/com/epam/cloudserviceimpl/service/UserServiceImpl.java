package com.epam.cloudserviceimpl.service;

import com.epam.cloudserviceimpl.exception.UserNotFoundException;
import com.epam.cloudserviceimpl.mapper.UserMapper;
import com.epam.jmpdto.entity.User;
import com.epam.jmpdto.entity.dto.UserRequestDto;
import com.epam.jmpdto.entity.dto.UserResponseDto;
import com.epam.jmpdto.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserServiceExtended {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public void deleteUser(Long userId) {
        //check
        getUserById(userId);

        log.info("Deleting user. ID: {}", userId);
        userRepository.deleteById(userId);
    }

    @Override
    public UserResponseDto getUser(Long userId) {
        User user = getUserById(userId);
        return userMapper.toUserResponseDto(user);
    }

    @Override
    public List<UserResponseDto> getAllUser() {
        log.info("Getting all users");

        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
            .map(userMapper::toUserResponseDto)
            .collect(Collectors.toList());
    }

    @Override
    public UserResponseDto updateUser(UserRequestDto userRequestDto) {
        log.info("Updating user. ID: {}", userRequestDto.getId());

        //check
        getUserById(userRequestDto.getId());

        User userToUpdate = userMapper.toUser(userRequestDto);
        User updatedUser = userRepository.save(userToUpdate);
        return userMapper.toUserResponseDto(updatedUser);
    }

    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        log.info("Creating a new user. Name: {}", userRequestDto.getName());
        User user = userMapper.toUser(userRequestDto);

        User savedUser = userRepository.save(user);
        return userMapper.toUserResponseDto(savedUser);
    }

    @Override
    public User getUserById(Long userId) {
        log.info("Getting user. ID: {}", userId);
        return userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException("User not found. id: {}" + userId));
    }
}
