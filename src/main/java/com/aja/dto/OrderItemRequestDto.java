package com.aja.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderItemRequestDto {
	
	private Long productid;
	private String productName;
	private Integer quantity;
	private Double price;

}
