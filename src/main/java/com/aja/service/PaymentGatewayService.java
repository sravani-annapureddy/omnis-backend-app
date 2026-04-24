package com.aja.service;

import java.util.Map;

public interface PaymentGatewayService {

    // Create Razorpay Order
    Map<String, Object> createOrder(Double amount, Long orderId);

    // Verify Razorpay Payment
    boolean verifyPayment(String razorpayOrderId,
                          String razorpayPaymentId,
                          String razorpaySignature);
}