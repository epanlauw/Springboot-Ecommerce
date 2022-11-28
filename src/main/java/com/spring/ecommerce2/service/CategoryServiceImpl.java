package com.spring.ecommerce2.service;

import com.spring.ecommerce2.entity.Category;
import com.spring.ecommerce2.exception.ResourceNotFoundException;
import com.spring.ecommerce2.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = {"category"})
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepo;

    @Override
    @Cacheable(unless = "#result == null")
    public Page<Category> getAllCategories(Pageable page) {
        return categoryRepo.findAll(page);
    }

    @Override
    @Cacheable(unless = "#result == null")
    public Category getCategoryById(Long id) {
        return categoryRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category with id " + id + " not found!"));
    }

    @Override
    @CachePut(unless = "#result == null")
    public Category createCategory(Category category) {
        return categoryRepo.save(category);
    }

    @Override
    @CacheEvict
    public Category updateCategory(Long id, Category category) {
        Category existingCategory = getCategoryById(id);

        existingCategory.setName(category.getName() != null ? category.getName() : existingCategory.getName());

        return categoryRepo.save(existingCategory);
    }

    @Override
    @CacheEvict
    public void deleteCategoryById(Long id) {
        Category category = getCategoryById(id);
        categoryRepo.deleteById(category.getId());
    }

    @Override
    public List<Category> readByName(String keyword, Pageable page) {
        return categoryRepo.findByNameContainingIgnoreCase(keyword, page).toList();
    }
}