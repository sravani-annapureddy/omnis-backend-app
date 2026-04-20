package com.aja.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserResponseDto {
	
	private Long id;
	
	private String fullName;
	
	private String email;
	
	private String phoneNumber;
	
	private String role;
	
	private Boolean isActive;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;

}
