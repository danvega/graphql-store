package com.wafflecorp.store.controller;

import com.wafflecorp.store.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureHttpGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureHttpGraphQlTester
public class ProductControllerHttpTest {

    @Autowired
    private GraphQlTester graphQlTester;

    @Test
    void shouldFindAllProducts() {
        graphQlTester.documentName("products")
                .execute()
                .path("allProducts")
                .entityList(Product.class)
                .hasSize(4);
    }

    @Test
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