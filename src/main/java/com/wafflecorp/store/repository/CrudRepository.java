package com.wafflecorp.store.repository;

import java.util.ArrayList;
import java.util.List;

public interface CrudRepository<T> {

    List<T> findAll();

    T findOne(Integer id);

    T save(T t);

    Boolean delete(Integer id);

}
