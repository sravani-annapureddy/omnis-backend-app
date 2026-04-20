package com.aja.service;

import com.aja.dto.AddCartItemRequestDto;
import com.aja.dto.CartResponseDto;

public interface CartService {
	
	CartResponseDto addItem(AddCartItemRequestDto requestDto);
	CartResponseDto getCartByUserId(Long id);
	CartResponseDto removeItem(Long userId, Long productId);
	void clearCart(Long userId);

}
