package com.wafflecorp.store.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wafflecorp.store.config.GraphQlConfig;
import com.wafflecorp.store.model.Product;
import com.wafflecorp.store.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.graphql.test.tester.GraphQlTester;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@Import({GraphQlConfig.class})
@GraphQlTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private GraphQlTester graphQlTester;
    @MockBean
    private ProductRepository productRepository;
    private List<Product> products = new ArrayList<>();


    @Test
    public void contextLoads() {
        assertNotNull(graphQlTester);
        assertNotNull(productRepository);
    }

    @BeforeEach
    void setUp() {
        if(products.isEmpty()) {
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<Product>> typeReference = new TypeReference<>() {
            };
            InputStream inputStream = TypeReference.class.getResourceAsStream("/data/products.json");
            try {
                products = mapper.readValue(inputStream,typeReference);
                System.out.println("Products loaded!");
            } catch (IOException e){
                System.out.println("Unable to save users: " + e.getMessage());
            }
        }
    }

    @Test
    void shouldGetAllProductsQueryReturnsAllProducts() {
        // language=GraphQL
        String document = """
            query {
              allProducts {
                id
                title
                desc
              }
            }
        """;

        when(productRepository.findAll()).thenReturn(products);

        graphQlTester.document(document)
                .execute()
                .path("allProducts")
                .entityList(Product.class)
                .hasSize(4);
    }

    @Test
    void shouldReturnProductWithValidID() {
        // language=GraphQL
        String document = """
            query findProduct($id:ID) {
              getProduct(id:$id) {
                id
                title
                desc
              }
            }
        """;

        when(productRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(products.get(0)));

        graphQlTester.document(document)
                .variable("id",1)
                .execute()
                .path("getProduct")
                .entity(Product.class)
                .satisfies(product -> {
                    assertEquals("Classic Waffle",product.getTitle());
                    assertEquals("Classic Sweet Cream Waffle",product.getDesc());
                });
    }

    @Test
    void shouldReturnValidProductWithDocumentName() {
        when(productRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(products.get(0)));

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