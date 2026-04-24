package com.aja.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.razorpay.RazorpayClient;

@Configuration
public class RazorpayConfig {

    @Value("${razorpay.key}")
    private String key;

    @Value("${razorpay.secret}")
    private String secret;

    @Bean
    public RazorpayClient razorpayClient() {
        try {
            return new RazorpayClient(key, secret);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create RazorpayClient", e);
        }
    }
}