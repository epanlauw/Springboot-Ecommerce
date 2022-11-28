package com.spring.ecommerce2.service;

import com.spring.ecommerce2.entity.Product;
import com.spring.ecommerce2.exception.ResourceNotFoundException;
import com.spring.ecommerce2.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@CacheConfig(cacheNames = {"product"})
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepo;

    @Override
    @Cacheable(unless = "#result == null")
    public Page<Product> getAllProducts(Pageable page) {
        return productRepo.findAll(page);
    }

    @Override
    @Cacheable(unless = "#result == null")
    public Product getProductById(Long id) {
        return productRepo.findById(id)
                            .orElseThrow(() -> new ResourceNotFoundException("Product with id " + " not found!!"));
    }

    @Override
    @CachePut(unless = "#result == null")
    public Product createProduct(Product product) {
        return productRepo.save(product);
    }

    @Override
    @CacheEvict
    public Product updateProduct(Long id, Product product) {
        Product existingProduct = getProductById(id);

        existingProduct.setName(product.getName() != null ? product.getName() : existingProduct.getName());
        existingProduct.setDescription(product.getDescription() != null ? product.getDescription() : existingProduct.getDescription());
        existingProduct.setImage(product.getImage() != null ? product.getImage() : existingProduct.getImage());
        existingProduct.setPrice(product.getPrice() != null ? product.getPrice() : existingProduct.getPrice());
        existingProduct.setStock(product.getStock() != null ? product.getStock() : existingProduct.getStock());
        existingProduct.setCategory(product.getCategory() != null ? product.getCategory() : existingProduct.getCategory());

        return productRepo.save(existingProduct);
    }

    @Override
    @CacheEvict
    public void deleteProductById(Long id) {
        Product product = getProductById(id);

        productRepo.deleteById(product.getId());
    }

    @Override
    @Cacheable(unless = "#result == null")
    public List<Product> readByName(String keyword, Pageable page) {
        return productRepo.findByNameContainingIgnoreCase(keyword, page).toList();
    }

    @Override
    @Cacheable(unless = "#result == null")
    public List<Product> readByPrice(BigDecimal minPrice, BigDecimal maxPrice, Pageable page) {
        if(minPrice == null) {
            minPrice = BigDecimal.ZERO;
        }

        if(maxPrice == null) {
            Product existingProduct = productRepo.findFirstByOrderByPriceDesc();
            maxPrice = existingProduct.getPrice();
        }

        return productRepo.findByPriceBetween(minPrice, maxPrice, page).toList();
    }

    @Override
    @Cacheable(unless = "#result == null")
    public List<Product> readByCategoryId(Long categoryId, Pageable page) {
        return productRepo.findByCategoryId(categoryId, page).toList();
    }

    @Override
    @Cacheable(unless = "#result == null")
    public List<Product> readByStock(Double minStock, Double maxStock, Pageable page) {
        if(minStock == null) {
            minStock = 0.0;
        }

        if(maxStock == null) {
            Product existingProduct = productRepo.findFirstByOrderByStockDesc();
            maxStock = existingProduct.getStock();
        }

        return productRepo.findByStockBetween(minStock, maxStock, page).toList();
    }


}