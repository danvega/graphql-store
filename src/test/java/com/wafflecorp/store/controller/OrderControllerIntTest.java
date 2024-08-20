package com.wafflecorp.store.controller;

import com.wafflecorp.store.order.Order;
import com.wafflecorp.store.order.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.graphql.test.tester.HttpGraphQlTester;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderControllerIntTest {

    private HttpGraphQlTester graphQlTester;
    @Autowired
    private OrderRepository orderRepository;

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        WebTestClient client = WebTestClient.bindToServer()
                .defaultHeaders(httpHeaders -> httpHeaders.setBasicAuth("user","password"))
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
        var document = """
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
                .hasSize(orderRepository.findAll().size());
    }

    @Test
    void shouldGetSingleOrder() {
        Order o = orderRepository.findAll().stream().findFirst().get();

        String document = """
            query findOrderById($id: ID) {
              getOrder(id: $id) {
                id
                qty
                status
                orderedOn
                product {
                  title
                }
                customer {
                  firstName
                  lastName
                  email
                }
              }
            }
        """;

        graphQlTester.document(document)
                .variable("id",o.getId())
                .execute()
                .path("getOrder")
                .entity(Order.class)
                .satisfies(order -> {
                    assertEquals(o.getId(),order.getId());
                    assertEquals(o.getQty(),order.getQty());
                    assertEquals(o.getStatus(),order.getStatus());
                    assertEquals(o.getOrderedOn(),order.getOrderedOn());
                    assertEquals(o.getProduct().getTitle(),order.getProduct().getTitle());
                    assertEquals(o.getCustomer().getEmail(),order.getCustomer().getEmail());
                });
    }

}