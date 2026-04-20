package com.aja.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderRequestDto {
	
	private Long userId;
	private String customerName;
	private String shippingAddress;
	private List<OrderItemRequestDto> items;

}
