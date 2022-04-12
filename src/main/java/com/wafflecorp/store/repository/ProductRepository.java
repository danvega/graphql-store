package com.wafflecorp.store.repository;

import com.wafflecorp.store.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product,Integer> {

}
