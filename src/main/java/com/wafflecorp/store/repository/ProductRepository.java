package com.wafflecorp.store.repository;

import com.wafflecorp.store.model.Product;
import org.springframework.data.repository.ListCrudRepository;

public interface ProductRepository extends ListCrudRepository<Product,Integer> {

}
