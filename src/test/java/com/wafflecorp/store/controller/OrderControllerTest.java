package com.wafflecorp.store.controller;

import com.wafflecorp.store.config.GraphQlConfig;
import com.wafflecorp.store.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.graphql.test.tester.GraphQlTester;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@GraphQlTest(OrderController.class)
@Import({GraphQlConfig.class})
class OrderControllerTest {

    @Autowired
    private GraphQlTester graphQlTester;

    @MockBean
    private OrderRepository orderRepository;

    @Test
    void contextLoads() {
        assertNotNull(graphQlTester);
    }



}