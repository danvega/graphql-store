package com.wafflecorp.store.repository;

import com.wafflecorp.store.model.Customer;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface CustomerRepository extends ListCrudRepository<Customer,Integer> {

    Customer findByLastName(String name);

}
