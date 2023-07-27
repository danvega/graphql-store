package com.wafflecorp.store.config;

import com.wafflecorp.store.model.Customer;
import com.wafflecorp.store.model.Order;
import com.wafflecorp.store.model.OrderStatus;
import com.wafflecorp.store.model.Product;
import com.wafflecorp.store.repository.CustomerRepository;
import com.wafflecorp.store.repository.OrderRepository;
import com.wafflecorp.store.repository.ProductRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class DatabaseInit implements ApplicationRunner {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    public DatabaseInit(ProductRepository productRepository, OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        var classic = new Product("Classic Waffle", "Classic Sweet Cream Waffle");
        var pecan = new Product("Pecan Waffle", "Sweet Cream Waffle made with delicious Pecan Pieces");
        var chocolateChip = new Product("Chocolate Chip Waffle", "Sweet Cream Waffle covered in Chocolate Chips");
        var peanutButter = new Product("Peanut Butter Chip Waffle", "Sweet Cream Waffle covered in Peanut Butter Chips");
        var products = List.of(classic,pecan,chocolateChip,peanutButter);
        productRepository.saveAll(products);

        Customer dan = new Customer("Dan","Vega","danvega@gmail.com");
        var andreJohnson = new Customer("Andre", "Johnson","ajohnson@gmail.com");
        var customers = List.of(dan,andreJohnson);
        customerRepository.saveAll(customers);

        List<Order> orders = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            orders.add(new Order(new Random().nextInt(10),
                    LocalDate.now().plusDays(new Random().nextInt(30)),
                    OrderStatus.values()[new Random().nextInt(OrderStatus.values().length)],
                    products.get(new Random().nextInt(products.size())),
                    customers.get(new Random().nextInt(customers.size()))));
        }
        orderRepository.saveAll(orders);

    }

}
