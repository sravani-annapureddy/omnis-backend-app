package com.aja.service;

import java.util.List;

import com.aja.dto.UserRequestDto;
import com.aja.dto.UserResponseDto;

public interface UserService {

    UserResponseDto createUser(UserRequestDto requestDto);

    UserResponseDto getUserById(Long id);

    List<UserResponseDto> getAllUsers();

    UserResponseDto updateUser(Long id, UserRequestDto requestDto);

    void deleteUser(Long id);
}