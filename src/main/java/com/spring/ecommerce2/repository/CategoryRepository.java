package com.spring.ecommerce2.repository;

import com.spring.ecommerce2.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    
}