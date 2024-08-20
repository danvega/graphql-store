package com.wafflecorp.store.customer;

import org.springframework.data.repository.ListCrudRepository;

public interface CustomerRepository extends ListCrudRepository<Customer,Integer> {

    Customer findByLastName(String name);

}
