package com.aja.service;

import com.razorpay.Order;

public interface RazorpayService {

    Order createOrder(Double amount);

}