package com.aja.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aja.entity.Payment;

public interface PaymentRepostitory extends JpaRepository<Payment, Long> {

}
