package com.wafflecorp.store.controller;

import com.wafflecorp.store.model.Order;
import com.wafflecorp.store.repository.OrderRepository;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class OrderController {

    private final OrderRepository repository;

    public OrderController(OrderRepository repository) {
        this.repository = repository;
    }

    @QueryMapping
    public List<Order> allOrders() {
        return repository.findAll();
    }
}
