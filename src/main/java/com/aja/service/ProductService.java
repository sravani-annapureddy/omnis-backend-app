package com.aja.service;

import java.util.List;

import com.aja.dto.ProductRequestDto;
import com.aja.dto.ProductResponseDto;

public interface ProductService {
	
	ProductResponseDto creatProduct(ProductRequestDto requestDto);
	ProductResponseDto getProductById(Long id);
	List<ProductResponseDto> getAllProducts();
	ProductResponseDto updateProduct(Long id, ProductRequestDto requestDto);
	void deleteProduct(Long id);

}
