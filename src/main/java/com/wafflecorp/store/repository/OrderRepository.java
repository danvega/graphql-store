package com.wafflecorp.store.repository;

import com.wafflecorp.store.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order,Integer> {

}
