package com.aja.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NotificationLogRequestDto {
	
	private Long userId;
	private String type;
	private String message;
	private String status;

}
