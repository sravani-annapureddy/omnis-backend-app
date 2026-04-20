package com.aja.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CartItemResponseDto {
	
	private Long id;
	private Long productId;
	private String productName;
	private Integer quantity;
	private Double price;

}
