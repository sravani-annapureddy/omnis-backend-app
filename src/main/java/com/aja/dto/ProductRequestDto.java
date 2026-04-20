package com.aja.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductRequestDto {
	
	private String name;
	private String description;
	private String category;
	private BigDecimal price;
	private Integer stock;
	private String imageUrl;
	private Boolean isActive;
	

}
