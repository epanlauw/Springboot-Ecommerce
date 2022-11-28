package com.spring.ecommerce2.repository;

import com.spring.ecommerce2.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    
    // SELECT FROM * tbl_category WHERE name LIKE '%keyword%'
    Page<Category> findByNameContainingIgnoreCase(String keyword, Pageable page);
}