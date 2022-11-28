package com.spring.ecommerce2.service;

import com.spring.ecommerce2.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {

    Page<Category> getAllCategories(Pageable page);

    Category getCategoryById(Long id);

    Category createCategory(Category category);

    Category updateCategory(Long id, Category category);

    void deleteCategoryById(Long id);

    List<Category> readByName(String keyword, Pageable page);
}