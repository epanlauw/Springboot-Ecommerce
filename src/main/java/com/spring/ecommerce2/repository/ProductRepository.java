package com.spring.ecommerce2.repository;

import com.spring.ecommerce2.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // SELECT * FROM tbl_product WHERE name LIKE '%keyword%'
    Page<Product> findByNameContainingIgnoreCase(String keyword, Pageable page);

    //SELECT * FROM tbl_product WHERE price BETWEEN 'minPrice' AND 'maxPrice'
    Page<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice, Pageable page);

    // SELECT * FROM tbl_product WHERE category_id=?
    Page<Product> findByCategoryId(Long categoryId, Pageable page);

    Product findFirstByOrderByPriceDesc();
}