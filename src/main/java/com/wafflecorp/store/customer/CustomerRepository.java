package com.wafflecorp.store.customer;

import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface CustomerRepository extends ListCrudRepository<Customer,Integer> {

    Customer findByLastName(String name);

    List<Customer> findAllByFirstNameOrLastName(String first, String last);
}
