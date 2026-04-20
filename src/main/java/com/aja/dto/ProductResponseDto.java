package com.aja.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductResponseDto {
	
	private Long id;
	private String name;
	private String description;
	private String category;
	private BigDecimal price;
	private Integer stock;
	private String imageUrl;
	private Boolean isActive;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	

}
