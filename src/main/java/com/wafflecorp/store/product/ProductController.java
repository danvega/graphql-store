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


}
