package com.pharma_assist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pharma_assist.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, String> {

}
