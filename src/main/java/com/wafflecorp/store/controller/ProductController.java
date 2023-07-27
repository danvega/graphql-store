package com.wafflecorp.store.controller;

import com.wafflecorp.store.model.Product;
import com.wafflecorp.store.model.ProductInput;
import com.wafflecorp.store.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.Arguments;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Controller
public class ProductController {

    private final ProductRepository repository;
    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @QueryMapping
    public List<Product> allProducts() {
        return repository.findAll();
    }

    @QueryMapping
    public Optional<Product> getProduct(@Argument Integer id) {
        return repository.findById(id);
    }

    @MutationMapping
    public Product createProduct(@Argument ProductInput productInput) {
        return repository.save(new Product(productInput.title(), productInput.desc()));
    }

}
