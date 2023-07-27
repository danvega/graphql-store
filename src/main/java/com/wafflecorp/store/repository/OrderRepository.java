package com.wafflecorp.store.repository;

import com.wafflecorp.store.model.Customer;
import com.wafflecorp.store.model.Order;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.ScrollPosition;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Window;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface OrderRepository extends ListCrudRepository<Order,Integer> {

    List<Order> findByCustomer(Customer customer);

    Window<Order> findByCustomer(Customer customer, ScrollPosition position, Limit limit, Sort sort);

}
