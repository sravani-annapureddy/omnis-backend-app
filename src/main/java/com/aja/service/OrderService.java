package com.aja.service;

import java.util.List;

import com.aja.dto.OrderRequestDto;
import com.aja.entity.Order;

public interface OrderService {
	
	Order placeOrder(OrderRequestDto dto);
	Order getOrderById(Long id);
	List<Order> getOrdersByUserId(Long userId);
	List<Order> getAllOrders();

}
