package com.wafflecorp.store.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);
    private final OrderRepository repository;

    public OrderController(OrderRepository repository) {
        this.repository = repository;
    }

    @QueryMapping
    public List<Order> allOrders(Principal principal) {
        log.info("Authenticated Principal: " + principal);
        return repository.findAll();
    }

    @QueryMapping
    public Optional<Order> getOrder(@Argument Integer id) {
        return repository.findById(id);
    }

}
