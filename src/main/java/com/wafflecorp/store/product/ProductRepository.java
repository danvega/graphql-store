package com.wafflecorp.store.product;

import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface ProductRepository extends ListCrudRepository<Product,Integer> {

    List<Product> findAllByTitleContaining(String title);
}
