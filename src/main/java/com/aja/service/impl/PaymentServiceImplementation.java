package com.aja.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aja.dto.PaymentRequestDto;
import com.aja.entity.Payment;
import com.aja.exception.ResourceNotFoundException;
import com.aja.repository.PaymentRepostitory;
import com.aja.service.PaymentService;

@Service
public class PaymentServiceImplementation implements PaymentService {
	
	private PaymentRepostitory PaymentRepo;

	@Override
	public Payment createPayment(PaymentRequestDto responseDto) {
		Payment payment=new Payment();
		payment.setOrderId(responseDto.getOrderId());
		payment.setPaymentMethod(responseDto.getPaymentMethod());
		payment.setAmount(responseDto.getAmount());
		payment.setPaymentStatus(responseDto.getPaymentStatus());
		payment.setTransactionReference(responseDto.getTransactionReference());
		return PaymentRepo.save(payment);
	}

	@Override
	public Payment getPaymentById(Long id) {
		return PaymentRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("payment not found by id: " + id));
		
	}

	@Override
	public List<Payment> getAllPayments() {
		
		return PaymentRepo.findAll();
	}

}
