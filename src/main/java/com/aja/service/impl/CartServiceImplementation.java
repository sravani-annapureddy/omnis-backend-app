package com.aja.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.aja.dto.AddCartItemRequestDto;
import com.aja.dto.CartItemResponseDto;
import com.aja.dto.CartResponseDto;
import com.aja.entity.Cart;
import com.aja.entity.CartItem;
import com.aja.repository.CartRepository;
import com.aja.service.CartService;

@Service
public class CartServiceImplementation implements CartService {
	
	private CartRepository CartRepo;

	@Override
	public CartResponseDto addItem(AddCartItemRequestDto requestDto) {

        Cart cart = CartRepo.findByUserId(requestDto.getUserId())
                .orElseGet(() -> {
                    Cart c = new Cart();
                    c.setUserId(requestDto.getUserId());
                    c.setItems(new ArrayList<>());
                    return c;
                });

        Optional<CartItem> existingItem = cart.getItems().stream()
                .filter(item -> item.getProductId().equals(requestDto.getProductId()))
                .findFirst();

        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + requestDto.getQuantity());
        } else {
            CartItem newItem = new CartItem();
            newItem.setProductId(requestDto.getProductId());
            newItem.setProductName(requestDto.getProductName());
            newItem.setQuantity(requestDto.getQuantity());
            newItem.setPrice(requestDto.getPrice());
            newItem.setCart(cart);

            cart.getItems().add(newItem);
        }

        Cart savedCart = CartRepo.save(cart);
        return map(savedCart);
    }

    private CartResponseDto map(Cart cart) {
        CartResponseDto responseDto = new CartResponseDto();
        responseDto.setCartId(cart.getId());
        responseDto.setUserId(cart.getUserId());

        List<CartItemResponseDto> itemDtos = cart.getItems().stream()
                .map(item -> {
                    CartItemResponseDto dto = new CartItemResponseDto();
                    dto.setId(item.getId());
                    dto.setProductId(item.getProductId());
                    dto.setProductName(item.getProductName());
                    dto.setQuantity(item.getQuantity());
                    dto.setPrice(item.getPrice());
                    return dto;
                })
                .collect(Collectors.toList());

        responseDto.setItems(itemDtos);

        double totalAmount = cart.getItems().stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();

        responseDto.setTotalAmount(totalAmount);

        return responseDto;
    }

    @Override
    public CartResponseDto getCartByUserId(Long id) {
        Cart cart = CartRepo.findByUserId(id)
                .orElseGet(() -> {
                    Cart c = new Cart();
                    c.setUserId(id);
                    c.setItems(new ArrayList<>());
                    return CartRepo.save(c);
                });

        return map(cart);
    }


	@Override
	public CartResponseDto removeItem(Long userId, Long productId) {
		Cart cart=CartRepo.findByUserId(userId).orElseThrow(()->new RuntimeException("Cart not fund for user id:" + userId));
		cart.getItems().removeIf(item->item.getProductId().equals(productId));
		
		Cart saveCart=CartRepo.save(cart);
		
		return map(saveCart);
	}

	@Override
	public void clearCart(Long userId) {
		Cart cart=CartRepo.findByUserId(userId).orElseThrow(()->new RuntimeException("Cart notfound for user id: " + userId));
		cart.getItems().clear();
		CartRepo.save(cart);

	}

}
