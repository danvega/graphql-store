package com.wafflecorp.store.product;

import com.wafflecorp.store.order.OrderRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {

    private final ProductRepository repository;
    private final OrderRepository orderRepository;

    public ProductController(ProductRepository repository, OrderRepository orderRepository) {
        this.repository = repository;
        this.orderRepository = orderRepository;
    }

    @QueryMapping
    public List<Product> allProducts() {
        return repository.findAll();
    }



    @QueryMapping
    public Optional<Product> getProduct(@Argument Integer id) {
        return Optional.ofNullable(repository.findById(id).orElseThrow(ProductNotFoundException::new));
    }

    @Secured("ROLE_ADMIN")
    @MutationMapping
    public Product createProduct(@Argument ProductInput productInput) {
        return repository.save(new Product(productInput.title(), productInput.desc()));
    }


//    @SchemaMapping
//    public List<Order> orders(Product product) {
//        return orderRepository.findAllByProductId(product.getId());
//    }

}
