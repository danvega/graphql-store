package com.wafflecorp.store.controller;

import com.wafflecorp.store.model.Customer;
import com.wafflecorp.store.model.Order;
import com.wafflecorp.store.repository.CustomerRepository;
import com.wafflecorp.store.repository.OrderRepository;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.ScrollPosition;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Window;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.graphql.data.query.ScrollSubrange;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CustomerController {

    private final CustomerRepository repository;
    private final OrderRepository orderRepository;

    public CustomerController(CustomerRepository repository, OrderRepository orderRepository) {
        this.repository = repository;
        this.orderRepository = orderRepository;
    }

    @QueryMapping
    public List<Customer> allCustomers() {
        return repository.findAll();
    }

    @QueryMapping
    public Customer findCustomerByLastName(@Argument String last) {
        return repository.findByLastName(last);
    }

    @SchemaMapping
    public List<Order> orders(Customer customer) {
        return orderRepository.findByCustomer(customer);
    }

    @SchemaMapping
    Window<Order> paginatedOrders(Customer customer, ScrollSubrange subrange) {
        ScrollPosition scrollPosition = subrange.position().orElse(ScrollPosition.offset());
        Limit limit = Limit.of(subrange.count().orElse(10));
        Sort sort = Sort.by("orderedOn").descending();
        return orderRepository.findByCustomer(customer, scrollPosition, limit, sort);
    }


}
