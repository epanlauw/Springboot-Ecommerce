package com.spring.ecommerce2.controller;

import com.spring.ecommerce2.entity.Product;
import com.spring.ecommerce2.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<Product> getAllProducts(Pageable page) {
        return productService.getAllProducts(page).toList();
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/products")
    public Product createProduct(@Valid @RequestBody Product product) {
        return productService.createProduct(product);
    }

    @PutMapping("/products/{id}")
    public Product updateProduct(@RequestBody Product product, @PathVariable Long id) {
        return productService.updateProduct(id, product);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("/products/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
    }

    @GetMapping("/products/name")
    public List<Product> getProductByName(@RequestParam String keyword, Pageable page) {
        return productService.readByName(keyword, page);
    }

    @GetMapping("/products/price")
    public List<Product> getProuctByPrice(@RequestParam(required = false) BigDecimal minPrice,
                                          @RequestParam(required = false) BigDecimal maxPrice, Pageable page) {
        return productService.readByPrice(minPrice, maxPrice, page);
    }

    @GetMapping("/products/category")
    public List<Product> getProductByCategory(@RequestParam Long categoryId, Pageable page) {
        return productService.readByCategoryId(categoryId, page);
    }

    @GetMapping("/products/stock")
    public List<Product> getProductByStock(@RequestParam(required = false) Double minStock,
                                           @RequestParam(required = false) Double maxStock, Pageable page) {
        return productService.readByStock(minStock, maxStock, page);
    }
}