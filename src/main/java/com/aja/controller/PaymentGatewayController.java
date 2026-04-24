package com.aja.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.aja.dto.PaymentRequestDto;
import com.aja.entity.Payment;
import com.aja.service.PaymentGatewayService;
import com.aja.service.PaymentService;

@RestController
@RequestMapping("/payment-gateway")
public class PaymentGatewayController {

    private final PaymentGatewayService paymentGatewayService;
    private final PaymentService paymentService;

    public PaymentGatewayController(PaymentGatewayService paymentGatewayService,
                                    PaymentService paymentService) {
        this.paymentGatewayService = paymentGatewayService;
        this.paymentService = paymentService;
    }

    @GetMapping("/create-order")
    public ResponseEntity<Map<String, Object>> createOrder(
            @RequestParam Double amount,
            @RequestParam Long orderId) {

        Map<String, Object> response =
                paymentGatewayService.createOrder(amount, orderId);

        return ResponseEntity.ok(response);
    }

    // ✅ 2. Verify Payment & Save
    @PostMapping("/verify")
    public ResponseEntity<?> verifyPayment(
            @RequestParam String razorpayOrderId,
            @RequestParam String razorpayPaymentId,
            @RequestParam String razorpaySignature,
            @RequestBody PaymentRequestDto dto) {

        boolean isValid = paymentGatewayService.verifyPayment(
                razorpayOrderId,
                razorpayPaymentId,
                razorpaySignature
        );

        if (!isValid) {
            return ResponseEntity.badRequest().body("Invalid signature");
        }

        // Save payment
        dto.setPaymentStatus("SUCCESS");
        dto.setTransactionReference(razorpayPaymentId);

        Payment payment = paymentService.createPayment(dto);

        return ResponseEntity.ok(payment);
    }
}