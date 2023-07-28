package com.wafflecorp.store.controller;

import com.wafflecorp.store.model.Product;
import com.wafflecorp.store.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureHttpGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.HttpGraphQlTester;
import org.springframework.security.test.context.support.WithMockUser;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureHttpGraphQlTester
public class ProductControllerHttpTest {

    @Autowired
    private HttpGraphQlTester graphQlTester;
    @Autowired
    private ProductRepository productRepository;

    @Test
    @WithMockUser
    void shouldFindAllProducts() {
        graphQlTester.documentName("products")
                .execute()
                .path("allProducts")
                .entityList(Product.class)
                .hasSize(productRepository.findAll().size());
    }

    @Test
    @WithMockUser
    void shouldReturnValidProductWithDocumentName() {
        graphQlTester.documentName("product")
                .variable("id",1)
                .execute()
                .path("getProduct")
                .entity(Product.class)
                .satisfies(product -> {
                    assertEquals("Classic Waffle",product.getTitle());
                    assertEquals("Classic Sweet Cream Waffle",product.getDesc());
                });
    }

}