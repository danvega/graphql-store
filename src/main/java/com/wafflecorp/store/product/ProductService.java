package com.wafflecorp.store.product;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PreAuthorize("hasRole('ADMIN')")
    Product create(Product product) {
        return productRepository.save(product);
    }
}
