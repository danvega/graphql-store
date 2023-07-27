package com.wafflecorp.store.service;

import com.wafflecorp.store.model.Product;
import com.wafflecorp.store.repository.ProductRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PreAuthorize("hasRole('ADMIN')")
    public Product create(Product product) {
        return productRepository.save(product);
    }
}
