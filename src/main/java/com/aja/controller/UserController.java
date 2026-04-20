package com.aja.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aja.dto.UserRequestDto;
import com.aja.dto.UserResponseDto;
import com.aja.service.impl.UserServiceImplementation;

@RestController
@RequestMapping("/user")
public class UserController {
	
	private UserServiceImplementation UserServiceImpl;
	
	public UserController(UserServiceImplementation userServiceImpl) {
		super();
		UserServiceImpl = userServiceImpl;
	}

	@PostMapping("/create")
	public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto requestDto) {
		return ResponseEntity.ok(UserServiceImpl.createUser(requestDto));
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long Id) {
		return ResponseEntity.ok(UserServiceImpl.getUserById(Id));
		
	}
	
	@GetMapping("{users}")
	public ResponseEntity<List<UserResponseDto>> getAllUsers(){
		return ResponseEntity.ok(UserServiceImpl.getAllUsers());
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id,@RequestBody UserRequestDto requestDto) {
		return ResponseEntity.ok(UserServiceImpl.updateUser(id, requestDto));
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletUser(@PathVariable Long id) {
		UserServiceImpl.deleteUser(id);
		return ResponseEntity.ok("User deleted Successfully");
	}

}
