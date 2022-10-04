package com.wafflecorp.store.controller;

import com.wafflecorp.store.model.Customer;
import com.wafflecorp.store.model.Order;
import com.wafflecorp.store.repository.CustomerRepository;
import com.wafflecorp.store.repository.OrderRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
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

}
