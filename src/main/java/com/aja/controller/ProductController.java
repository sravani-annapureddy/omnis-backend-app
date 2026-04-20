package com.aja.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aja.dto.ProductRequestDto;
import com.aja.dto.ProductResponseDto;
import com.aja.service.impl.ProductServiceImplementation;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	private ProductServiceImplementation ProductServiceImpl;
	
	@PostMapping("/create")
	public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductRequestDto requestDto) {
		return ResponseEntity.ok(ProductServiceImpl.creatProduct(requestDto));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProductResponseDto> getProductById(@PathVariable Long id) {
		return ResponseEntity.ok(ProductServiceImpl.getProductById(id));
		
	}
	
	@GetMapping
	public ResponseEntity<List<ProductResponseDto>> getAllProducts(){
		return ResponseEntity.ok(ProductServiceImpl.getAllProducts());
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ProductResponseDto> updatedProduct(@PathVariable Long id,@RequestBody ProductRequestDto requestDto) {
		return ResponseEntity.ok(ProductServiceImpl.updateProduct(id, requestDto));
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteProducts(@PathVariable Long id) {
		ProductServiceImpl.deleteProduct(id);
		return ResponseEntity.ok("Product deleted successfully");
	}

}
