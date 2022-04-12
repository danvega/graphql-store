package com.wafflecorp.store.controller;

import com.wafflecorp.store.model.Order;
import com.wafflecorp.store.repository.OrderRepository;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class OrderController {

    private final OrderRepository repository;

    public OrderController(OrderRepository repository) {
        this.repository = repository;
    }

    @QueryMapping
    public Iterable<Order> allOrders() {
        return repository.findAll();
    }

}
