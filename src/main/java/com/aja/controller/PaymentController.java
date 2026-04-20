package com.aja.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aja.dto.PaymentRequestDto;
import com.aja.entity.Payment;
import com.aja.service.impl.PaymentServiceImplementation;
@RestController
@RequestMapping("/payments")
public class PaymentController {
	@Autowired
	private PaymentServiceImplementation PaymentServiceImpl;
	
	@PostMapping("/create")
	public ResponseEntity<Payment> createPayment(@RequestBody PaymentRequestDto RequestDto){
		return ResponseEntity.ok(PaymentServiceImpl.createPayment(RequestDto));
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Payment> getById(@PathVariable Long id){
		return ResponseEntity.ok(PaymentServiceImpl.getPaymentById(id));
		
	}
	
	@GetMapping
	public ResponseEntity<List<Payment>> getAll(){
		return ResponseEntity.ok(PaymentServiceImpl.getAllPayments());
	}
}
