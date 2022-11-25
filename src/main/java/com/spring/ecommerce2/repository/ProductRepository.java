package com.spring.ecommerce2.repository;

import com.spring.ecommerce2.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}