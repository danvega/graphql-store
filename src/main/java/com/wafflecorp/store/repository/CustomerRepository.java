package com.wafflecorp.store.repository;

import com.wafflecorp.store.model.Customer;
import com.wafflecorp.store.model.Order;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.Set;

@Repository
public class CustomerRepository implements CrudRepository<Customer> {

    private final OrderRepository orderRepository;

    private List<Customer> customers = new ArrayList<>();

    public CustomerRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Customer> findAll() {
        return customers;
    }

    public Customer findOne(Integer id) {
        return customers.stream().filter(customer -> customer.getId() == id).findFirst().orElse(null);
    }

    public Customer findByLastName(String last) {
        return customers.stream().filter(customer -> customer.getLastName().equals(last)).findFirst().orElse(null);
    }

    public Customer save(Customer Customer) {
        if(Customer.getId() == null) {
            OptionalInt max = customers.stream().mapToInt(o -> o.getId()).max();
            Customer.setId(max.getAsInt()+1);
            customers.add(Customer);
        } else {
            Customer existing = findOne(Customer.getId());
            if(existing != null) {
                int i = customers.indexOf(existing);
                customers.set(i,Customer);
            }
        }
        return null;
    }

    public Boolean delete(Integer id) {
        Customer existing = findOne(id);
        if(existing != null) {
            int i = customers.indexOf(existing);
            customers.remove(customers.get(i));
            return true;
        }
        return false;
    }

    @PostConstruct
    private void init() {
        Customer customer = new Customer(1,"Dan","Vega","danvega@gmail.com");
        Set<Order> orders = orderRepository.findByCustomerId(1);
        customer.setOrders(orders);
        customers.add(customer);
    }

}
