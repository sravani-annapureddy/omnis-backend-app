package com.aja.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aja.dto.UserRequestDto;
import com.aja.dto.UserResponseDto;
import com.aja.entity.User;
import com.aja.exception.ResourceNotFoundException;
import com.aja.repository.UserRepository;
import com.aja.service.UserService;

@Service
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    

    public UserServiceImplementation(UserRepository userRepo, PasswordEncoder passwordEncoder) {
		super();
		this.userRepo = userRepo;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
    public UserResponseDto createUser(UserRequestDto requestDto) {
        if (userRepo.existsByEmail(requestDto.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        if (userRepo.existsByPhoneNumber(requestDto.getPhoneNumber())) {
            throw new IllegalArgumentException("Phone number already exists");
        }

        User user = new User();
        user.setFullName(requestDto.getFullName());
        user.setEmail(requestDto.getEmail());
        user.setPhoneNumber(requestDto.getPhoneNumber());
        user.setPassword(requestDto.getPassword());
        user.setRole(requestDto.getRole());
        user.setIsActive(requestDto.getIsActive());

        User savedUser = userRepo.save(user);
        return mapToResponseDto(savedUser);
    }

    @Override
    public UserResponseDto getUserById(Long id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return mapToResponseDto(user);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userRepo.findAll()
                .stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDto updateUser(Long id, UserRequestDto requestDto) {
        User existingUser = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + id));

        if (!existingUser.getEmail().equals(requestDto.getEmail())
                && userRepo.existsByEmail(requestDto.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        if (!existingUser.getPhoneNumber().equals(requestDto.getPhoneNumber())
                && userRepo.existsByPhoneNumber(requestDto.getPhoneNumber())) {
            throw new IllegalArgumentException("Phone number already exists");
        }

        existingUser.setFullName(requestDto.getFullName());
        existingUser.setEmail(requestDto.getEmail());
        existingUser.setPassword(requestDto.getPassword());
        existingUser.setPhoneNumber(requestDto.getPhoneNumber());
        existingUser.setRole(requestDto.getRole());
        existingUser.setIsActive(requestDto.getIsActive());

        User updatedUser = userRepo.save(existingUser);
        return mapToResponseDto(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        User existingUser = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        userRepo.delete(existingUser);
    }

    private UserResponseDto mapToResponseDto(User user) {
        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setId(user.getId());
        responseDto.setFullName(user.getFullName());
        responseDto.setEmail(user.getEmail());
        responseDto.setPhoneNumber(user.getPhoneNumber());
        responseDto.setRole(user.getRole());
        responseDto.setIsActive(user.getIsActive());
        responseDto.setCreatedAt(user.getCreatedAt());
        responseDto.setUpdatedAt(user.getUpdatedAt());
        return responseDto;
    }
}