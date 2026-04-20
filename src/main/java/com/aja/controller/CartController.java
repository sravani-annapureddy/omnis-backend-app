package com.aja.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aja.dto.AddCartItemRequestDto;
import com.aja.dto.CartResponseDto;
import com.aja.service.impl.CartServiceImplementation;

@RestController
@RequestMapping("/carts")
public class CartController {
	
	private CartServiceImplementation CartServcieImpl;
	
	@PostMapping("/add")
	public ResponseEntity<CartResponseDto> addItem(@RequestBody AddCartItemRequestDto requestDto) {
		
		return ResponseEntity.ok(CartServcieImpl.addItem(requestDto));
		
	}
	
	@GetMapping("/userId")
	public ResponseEntity<CartResponseDto> getCart(@PathVariable Long userId){
		return ResponseEntity.ok(CartServcieImpl.getCartByUserId(userId));
	}
	
	@DeleteMapping("/{userId}/items/{productId}")
	public ResponseEntity<CartResponseDto> removeItem(@PathVariable Long userId,@PathVariable Long productId){
		return ResponseEntity.ok(CartServcieImpl.removeItem(userId, productId));
		
	}
	
	@DeleteMapping("/{userId}/clear")
	public ResponseEntity<String> clearCart(@PathVariable Long userId){
		CartServcieImpl.clearCart(userId);
		return ResponseEntity.ok("Cart cleared Successfully");
		
	}
}
