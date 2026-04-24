package com.aja.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.aja.service.PaymentGatewayService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.Utils;

@Service
public class PaymentGatewayServiceImplementation implements PaymentGatewayService {

    private final RazorpayClient razorpayClient;

    @Value("${razorpay.secret}")
    private String secret;

    public PaymentGatewayServiceImplementation(RazorpayClient razorpayClient) {
        this.razorpayClient = razorpayClient;
    }

    @Override
    public Map<String, Object> createOrder(Double amount, Long orderId) {
        try {
            JSONObject options = new JSONObject();
            options.put("amount", amount * 100); // paise
            options.put("currency", "INR");
            options.put("receipt", "order_" + orderId);

            Order order = razorpayClient.orders.create(options);

            Map<String, Object> response = new HashMap<>();
            response.put("orderId", order.get("id"));
            response.put("amount", order.get("amount"));
            response.put("currency", order.get("currency"));

            return response;

        } catch (Exception e) {
            throw new RuntimeException("Error creating Razorpay order", e);
        }
    }

    @Override
    public boolean verifyPayment(String razorpayOrderId,
                                 String razorpayPaymentId,
                                 String razorpaySignature) {
        try {
            String data = razorpayOrderId + "|" + razorpayPaymentId;
            String generatedSignature = Utils.getHash(data, secret);

            return generatedSignature.equals(razorpaySignature);

        } catch (Exception e) {
            throw new RuntimeException("Payment verification failed", e);
        }
    }
}