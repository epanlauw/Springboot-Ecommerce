package com.spring.ecommerce2.service;

import com.spring.ecommerce2.entity.Product;
import com.spring.ecommerce2.exception.ResourceNotFoundException;
import com.spring.ecommerce2.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepo;

    @Override
    public Page<Product> getAllProducts(Pageable page) {
        return productRepo.findAll(page);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepo.findById(id)
                            .orElseThrow(() -> new ResourceNotFoundException("Product with id " + " not found!!"));
    }

    @Override
    public Product createProduct(Product product) {
        return productRepo.save(product);
    }

    @Override
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
    public void deleteProductById(Long id) {
        Product product = getProductById(id);

        productRepo.deleteById(product.getId());
    }
}