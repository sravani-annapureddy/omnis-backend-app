package com.aja.service.impl;

import java.util.List;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aja.dto.ProductRequestDto;
import com.aja.dto.ProductResponseDto;
import com.aja.entity.Product;
import com.aja.exception.ResourceNotFoundException;
import com.aja.repository.ProductRepository;
import com.aja.service.ProductService;

@Service
public class ProductServiceImplementation implements ProductService {
	@Autowired
	private ProductRepository ProductRepo;

	@Override
	public ProductResponseDto creatProduct(ProductRequestDto requestDto) {
		Product product= new Product();
		product.setName(requestDto.getName());
		product.setDescription(requestDto.getDescription());
		product.setCategory(requestDto.getCategory());
		product.setPrice(requestDto.getPrice());
		product.setStock(requestDto.getStock());
		product.setImageUrl(requestDto.getImageUrl());
		product.setIsActive(requestDto.getIsActive());
	
		return map(ProductRepo.save(product));
	}

	private ProductResponseDto map(Product product) {
		ProductResponseDto responseDto=new ProductResponseDto();
		responseDto.setId(product.getId());
		responseDto.setName(product.getName());
		responseDto.setDescription(product.getDescription());
		responseDto.setCategory(product.getCategory());
		responseDto.setPrice(product.getPrice());
		responseDto.setStock(product.getStock());
		responseDto.setIsActive(product.getIsActive());
		responseDto.setCreatedAt(product.getCreatedAt());
		responseDto.setUpdatedAt(product.getUpdatedAt());
		return responseDto;
		
	}
        
                

	@Override
	public ProductResponseDto getProductById(Long id) {
		// TODO Auto-generated method stub
		return map(ProductRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product not found with id:" + id)));
	}

	@Override
	public List<ProductResponseDto> getAllProducts() {
		// TODO Auto-generated method stub
		return ProductRepo.findAll().stream().map(this::map).collect(Collectors.toList());
		
		
	}

	@Override
	public ProductResponseDto updateProduct(Long id, ProductRequestDto requestDto) {
		Product product=ProductRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product Not Fond with id:" + id));
		
		product.setName(requestDto.getName());
		product.setDescription(requestDto.getDescription());
		product.setCategory(requestDto.getCategory());
		product.setPrice(requestDto.getPrice());
		product.setStock(requestDto.getStock());
		product.setImageUrl(requestDto.getImageUrl());
		product.setIsActive(requestDto.getIsActive());
		return map(ProductRepo.save(product));
	}

	@Override
	public void deleteProduct(Long id) {
		Product product=ProductRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product Not Found with id: +id"));
		ProductRepo.delete(product);
        
	}

}
