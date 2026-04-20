package com.aja.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CartResponseDto {
	
	private Long cartId;
	private Long userId;
	private List<CartItemResponseDto> items;
	private Double totalAmount;

}
