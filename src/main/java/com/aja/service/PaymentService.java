package com.aja.service;

import java.util.List;

import com.aja.dto.PaymentRequestDto;
import com.aja.entity.Payment;

public interface PaymentService {
	
	Payment createPayment(PaymentRequestDto responseDto);
	Payment getPaymentById(Long id);
	List<Payment> getAllPayments();

}
