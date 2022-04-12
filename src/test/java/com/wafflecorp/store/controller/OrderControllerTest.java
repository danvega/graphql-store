package com.wafflecorp.store.controller;

import com.wafflecorp.store.config.GraphQlConfig;
import com.wafflecorp.store.model.Order;
import com.wafflecorp.store.model.Product;
import com.wafflecorp.store.repository.OrderRepository;
import com.wafflecorp.store.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.context.annotation.Import;
import org.springframework.graphql.client.GraphQlClient;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.graphql.test.tester.WebGraphQlTester;
import org.springframework.graphql.web.WebGraphQlHandler;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@GraphQlTest(OrderController.class)
@Import({GraphQlConfig.class, OrderRepository.class, ProductRepository.class})
class OrderControllerTest {

    @Autowired
    private GraphQlTester graphQlTester;

    @Test
    void contextLoads() {
        assertNotNull(graphQlTester);
    }

    @Test
    void testGetAllOrdersQueryReturnsOneOrder() {
        String document = """
            query findAllOrders {
              allOrders {
                id
                status
                product {
                  title
                  desc
                }
                qty
                customer {
                  firstName
                  lastName
                  email
                }
              }
            } 
        """;

        graphQlTester.document(document)
                .execute()
                .path("allOrders")
                .entityList(Order.class)
                .hasSizeGreaterThan(0);
    }



}