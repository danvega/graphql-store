package com.wafflecorp.store.repository;

import com.wafflecorp.store.model.Customer;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface CustomerRepository extends Repository<Customer,Integer> {

    List<Customer> findAll();

    Customer findByLastName(String last);

    void save(Customer customer);

}
