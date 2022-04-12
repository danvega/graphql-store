package com.wafflecorp.store.repository;

import com.wafflecorp.store.model.Customer;
import com.wafflecorp.store.model.Order;
import com.wafflecorp.store.model.OrderStatus;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class OrderRepository implements CrudRepository<Order> {

    private final ProductRepository productRepository;

    private List<Order> orders = new ArrayList<>();

    public OrderRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Order> findAll() {
        return orders;
    }

    public Order findOne(Integer id) {
        return orders.stream().filter(order -> order.getId() == id).findFirst().orElse(null);
    }

    public Set<Order> findByCustomerId(Integer id) {
        return orders.stream().filter(order -> order.getCustomer().getId() == id).collect(Collectors.toSet());
    }

    public Order save(Order order) {
        if(order.getId() == null) {
            OptionalInt max = orders.stream().mapToInt(o -> o.getId()).max();
            order.setId(max.getAsInt()+1);
            orders.add(order);
        } else {
            Order existing = findOne(order.getId());
            if(existing != null) {
                int i = orders.indexOf(existing);
                orders.set(i,order);
            }
        }
        return order;
    }

    public Boolean delete(Integer id) {
        Order existing = findOne(id);
        if(existing != null) {
            int i = orders.indexOf(existing);
            orders.remove(orders.get(i));
            return true;
        }
        return false;
    }

    @PostConstruct
    private void init() {
        Order order = new Order(1,
                productRepository.findOne(1),
                1,
                new Customer(1,"Dan","Vega","danvega@gmail.com"),
                LocalDate.now(),
                OrderStatus.PENDING);

        orders.add(order);

    }
}
