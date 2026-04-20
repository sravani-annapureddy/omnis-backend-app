package com.aja.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.aja.dto.OrderRequestDto;
import com.aja.entity.Order;
import com.aja.entity.OrderItem;
import com.aja.exception.ResourceNotFoundException;
import com.aja.repository.OrderRepository;
import com.aja.service.OrderService;

@Service
public class OrderServiceImplementation implements OrderService {
	
	private OrderRepository OrderRepo;

	@Override
	public Order placeOrder(OrderRequestDto dto) {
		Order order=new Order();
		order.setUserId(dto.getUserId());
		order.setCustomerName(dto.getCustomerName());
		order.setShippingAddress(dto.getShippingAddress());
		
		List<OrderItem> items=dto.getItems().stream()
				.map(i->{
					OrderItem item=new OrderItem();
					item.setProductId(i.getProductid());
					item.setProductName(i.getProductName());
					item.setQuantity(i.getQuantity());
					item.setPrice(i.getPrice());
					item.setOrder(order);
					return item;
				})
				.collect(Collectors.toList());
		order.setItems(items);
		
		double total=dto.getItems().stream()
				.mapToDouble(i-> i.getPrice()* i.getQuantity())
				.sum();
		order.setTotalAmount(total);
		order.setOrderStatus("PLACED");
		order.setPaymentStatus("PENDING");
		return OrderRepo.save(order);
	}

	@Override
	public Order getOrderById(Long id) {
		
		return OrderRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Order not found with id: " + id));
	}

	@Override
	public List<Order> getOrdersByUserId(Long userId) {
		
		return OrderRepo.findByUserId(userId);
	}

	@Override
	public List<Order> getAllOrders() {
		
		return OrderRepo.findAll();
	}

}
