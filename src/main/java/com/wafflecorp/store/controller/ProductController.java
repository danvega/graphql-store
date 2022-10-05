package com.wafflecorp.store.controller;

import com.wafflecorp.store.model.Product;
import com.wafflecorp.store.repository.ProductRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class ProductController {

    private final ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @SchemaMapping(typeName = "Query", value = "allProducts")
    public Iterable<Product> allProducts() {
        return repository.findAll();
    }

    @QueryMapping
    public Optional<Product> getProduct(@Argument Integer id) {
        return repository.findById(id);
    }

}
