package com.aja.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aja.dto.OrderRequestDto;
import com.aja.entity.Order;
import com.aja.service.impl.OrderServiceImplementation;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	private OrderServiceImplementation OrderServiceImpl;
	
	@PostMapping
	public ResponseEntity<Order> placeOder(@RequestBody OrderRequestDto dto){
		Order order=OrderServiceImpl.placeOrder(dto);
		return ResponseEntity.ok(order);
		
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Order> getOrderById(@PathVariable Long id){
		return ResponseEntity.ok(OrderServiceImpl.getOrderById(id));
		
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Order>> getOrderByUser(@PathVariable Long userId){
		return ResponseEntity.ok(OrderServiceImpl.getOrdersByUserId(userId));
	}
	
	@GetMapping
	public ResponseEntity<List<Order>> getAllOrders(){
		return ResponseEntity.ok(OrderServiceImpl.getAllOrders());
		
	}

}
