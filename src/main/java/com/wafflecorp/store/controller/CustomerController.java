package com.wafflecorp.store.controller;

import com.wafflecorp.store.model.Customer;
import com.wafflecorp.store.repository.CustomerRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CustomerController {

    private final CustomerRepository repository;

    public CustomerController(CustomerRepository repository) {
        this.repository = repository;
    }

    @QueryMapping
    public List<Customer> allCustomers() {
        return repository.findAll();
    }

    @QueryMapping
    public Customer findCustomerByLastName(@Argument String last) {
        return repository.findByLastName(last);
    }

}
