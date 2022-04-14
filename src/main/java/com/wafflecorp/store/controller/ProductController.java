package com.wafflecorp.store.controller;

import com.wafflecorp.store.model.Product;
import com.wafflecorp.store.repository.ProductRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import javax.swing.text.html.Option;
import java.util.Optional;

@Controller
public class ProductController {

    private final ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @QueryMapping
    public Iterable<Product> allProducts() {
        return repository.findAll();
    }

    @QueryMapping
    public Optional<Product> getProduct(@Argument Integer id) {
        return repository.findById(id);
    }

    @MutationMapping
    public Product createProduct(@Argument Product input) {
        return repository.save(input);
    }

    @MutationMapping
    public Product updateProduct(@Argument Product input) {
        return repository.save(input);
    }

    @MutationMapping
    public void deleteProduct(@Argument Integer id) {
        repository.deleteById(id);
    }

}
