package com.wafflecorp.store.product;

import com.wafflecorp.store.order.Order;
import com.wafflecorp.store.order.OrderRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public ProductController(ProductRepository repository, OrderRepository orderRepository) {
        this.productRepository = repository;
        this.orderRepository = orderRepository;
    }

    @QueryMapping
    public List<Product> allProducts() {
        return productRepository.findAll();
    }

    @QueryMapping
    public Optional<Product> getProduct(@Argument Integer id) {
        return Optional.ofNullable(productRepository.findById(id).orElseThrow(ProductNotFoundException::new));
    }

    @MutationMapping
    public Product createProduct(@Argument ProductInput productInput) {
        return productRepository.save(new Product(productInput.title(), productInput.desc()));
    }

    @SchemaMapping
    public List<Order> orders(Product product) {
        return orderRepository.findAllByProductId(product.getId());
    }

}
