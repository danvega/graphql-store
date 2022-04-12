package com.wafflecorp.store.repository;

import com.wafflecorp.store.model.Product;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;

@Repository
public class ProductRepository implements CrudRepository<Product> {

    private List<Product> products = new ArrayList<>();

    public List<Product> findAll() {
        return products;
    }

    public Product findOne(Integer id) {
        return products.stream().filter(product -> product.getId() == id).findFirst().orElse(null);
    }

    public Product save(Product product) {
        if(product.getId() == null ) {
            // create
            OptionalInt max = products.stream().mapToInt(p -> p.getId()).max();
            product.setId(max.getAsInt()+1);
            products.add(product);
        } else {
            // update
            Product existing = findOne(product.getId());
            if(existing != null) {
                int i = products.indexOf(existing);
                products.set(i,product);
            }
        }
        return product;
    }

    public Boolean delete(Integer id) {
        Product existing = findOne(id);
        if(existing != null) {
            int i = products.indexOf(existing);
            products.remove(products.get(i));
            return true;
        }
        return false;
    }

    @PostConstruct
    private void init() {
        products.add(new Product(1,"Yummy 😋 Waffles","These are the best waffles ever!"));
    }

}
