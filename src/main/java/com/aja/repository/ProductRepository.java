package com.aja.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aja.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
