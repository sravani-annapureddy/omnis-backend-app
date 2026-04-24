package com.aja.service.impl;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.aja.service.RazorpayService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;

@Service
public class RazorpayServiceImplementation implements RazorpayService {

    @Value("${razorpay.key}")
    private String key;

    @Value("${razorpay.secret}")
    private String secret;

    @Override
    public Order createOrder(Double amount) {
        try {
            RazorpayClient client = new RazorpayClient(key, secret);

            JSONObject options = new JSONObject();
            options.put("amount", amount * 100);
            options.put("currency", "INR");
            options.put("receipt", "txn_" + System.currentTimeMillis());

            return client.orders.create(options);

        } catch (Exception e) {
            throw new RuntimeException("Error creating Razorpay order", e);
        }
    }
}