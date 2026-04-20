package com.aja.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRequestDto {
	
	private String fullName;
	
	private String email;
	
	private String phoneNumber;
	
	private String password;
	
	private String role;
	
	private Boolean isActive;

}
