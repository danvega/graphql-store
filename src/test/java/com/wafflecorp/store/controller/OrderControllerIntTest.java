package com.wafflecorp.store.controller;

import com.wafflecorp.store.model.Order;
import com.wafflecorp.store.model.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.graphql.test.tester.HttpGraphQlTester;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * An example of using HttpGraphQlTester
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderControllerIntTest {

    private HttpGraphQlTester graphQlTester;

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        WebTestClient client = WebTestClient.bindToServer()
                .baseUrl(String.format("http://localhost:%s/graphql", port))
                .build();

        graphQlTester = HttpGraphQlTester.create(client);
    }

    @Test
    void contextLoads() {
        assertNotNull(graphQlTester);
    }

    @Test
    void shouldRespondWithAllOrders() {
        String document = """
            query {
                allOrders {
                    id
                    qty
                    status
                }
            }        
        """;

        graphQlTester.document(document)
                .execute()
                .path("allOrders")
                .entityList(Order.class)
                .hasSize(1);
    }

    @Test
    void shouldGetSingleOrder() {
        String document = """
            query findOrderById($id: ID){
              getOrder(id: $id){
                id
                qty
                status
              }
            }
        """;

        graphQlTester.document(document)
                .variable("id",6)
                .execute()
                .path("getOrder")
                .entity(Order.class)
                .satisfies(order -> {
                    assertNotNull(order.getId());
                    assertEquals(1,order.getQty());
                    assertEquals(OrderStatus.PENDING,order.getStatus());
                });
    }

}