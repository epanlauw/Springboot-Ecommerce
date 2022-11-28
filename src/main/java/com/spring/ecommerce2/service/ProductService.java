package com.spring.ecommerce2.service;

import com.spring.ecommerce2.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    Page<Product> getAllProducts(Pageable page);

    Product getProductById(Long id);

    Product createProduct(Product product);

    Product updateProduct(Long id, Product product);

    void deleteProductById(Long id);

    List<Product> readByName(String keyword, Pageable page);

    List<Product> readByPrice(BigDecimal minPrice, BigDecimal maxPrice, Pageable page);

    List<Product> readByCategoryId(Long categoryId, Pageable page);
}