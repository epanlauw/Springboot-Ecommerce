package com.spring.ecommerce2.repository;

import com.spring.ecommerce2.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

}