package com.wafflecorp.store.config;

import com.wafflecorp.store.model.*;
import com.wafflecorp.store.repository.CustomerRepository;
import com.wafflecorp.store.repository.OrderRepository;
import com.wafflecorp.store.repository.ProductRepository;
import com.wafflecorp.store.repository.ReviewRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class DatabaseInit implements ApplicationRunner {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ReviewRepository reviewRepository;

    public DatabaseInit(ProductRepository productRepository, OrderRepository orderRepository, CustomerRepository customerRepository, ReviewRepository reviewRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        var classic = new Product("Classic Waffle", "Classic Sweet Cream Waffle");
        var pecan = new Product("Pecan Waffle", "Sweet Cream Waffle made with delicious Pecan Pieces");
        var chocolateChip = new Product("Chocolate Chip Waffle", "Sweet Cream Waffle covered in Chocolate Chips");
        var peanutButter = new Product("Peanut Butter Chip Waffle", "Sweet Cream Waffle covered in Peanut Butter Chips");
        productRepository.saveAll(List.of(classic,pecan,chocolateChip,peanutButter));

        Customer dan = new Customer("Dan","Vega","danvega@gmail.com");
        customerRepository.save(dan);

        Order one = new Order(1, LocalDate.now(), OrderStatus.PENDING,classic,dan);
        orderRepository.save(one);

        Review review = new Review("The best waffle ever!!!", ReviewStatus.PENDING, classic, dan);
        reviewRepository.save(review);
    }

}
