package com.wafflecorp.store.controller;

import com.wafflecorp.store.model.Customer;
import com.wafflecorp.store.model.Order;
import com.wafflecorp.store.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureHttpGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.HttpGraphQlTester;
import org.springframework.security.test.context.support.WithMockUser;

@SpringBootTest
@AutoConfigureHttpGraphQlTester
class CustomerControllerHttpTest {

    @Autowired
    private HttpGraphQlTester graphQlTester;
    @Autowired
    private CustomerRepository customerRepository;

    @Test
    @WithMockUser
    void shouldReturnAllCustomersWithoutOrders() {
        // language=graphql
        var document = """
        query {
            allCustomers {
                 id
                 firstName
                 lastName
                 email
            }
        }
        """;

        graphQlTester.document(document)
                .execute()
                .path("allCustomers")
                .entityList(Customer.class)
                .hasSize(customerRepository.findAll().size());
    }

    @WithMockUser
    @Test
    void shouldReturnCustomerWithOnlyTheirOrders() {
        String lastName = customerRepository.findAll().stream().findFirst().get().getLastName();

        // language=graphql
        var document = """
        query GetCustomer($last: String) {
            findCustomerByLastName(last: $last) {
                id
                firstName
                lastName
                email
                orders {
                    id
                    qty
                    status
                    orderedOn
                    customer {
                        id         
                    }
                }
            }
        }
        """;

        graphQlTester.document(document)
                .variable("last",lastName)
                .execute()
                .path("findCustomerByLastName")
                .entity(Customer.class)
                .satisfies(customer -> {
                    // I should not see orders for other customers
                    customer.getOrders().forEach(o -> o.getCustomer().getId().equals(customer.getId()));
                });

    }

    @Test
    @WithMockUser
    void shouldReturnedCustomerWithPaginatedOrders() {
        String lastName = customerRepository.findAll().stream().findFirst().get().getLastName();
        var limit = 10;

        // language=graphql
        var document = """
            query GetCustomer($last: String, $limit:Int) {
              findCustomerByLastName(last: $last) {
                id
                firstName
                lastName
                email
                paginatedOrders(first: $limit) {
                  edges {
                    node {
                      id
                      qty
                      status
                      orderedOn
                    }
                  }
                }
              }
            }
        """;

        graphQlTester.document(document)
                .variable("last",lastName)
                .variable("limit",limit)
                .execute()
                .path("findCustomerByLastName.paginatedOrders.edges")
                .entityList(Order.class)
                .hasSize(limit);
    }

}