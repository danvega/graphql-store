package com.wafflecorp.store.controller;

import com.wafflecorp.store.config.GraphQlConfig;
import com.wafflecorp.store.model.Product;
import com.wafflecorp.store.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.context.annotation.Import;
import org.springframework.graphql.test.tester.GraphQlTester;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SuppressWarnings("ALL")
@GraphQlTest(ProductController.class)
@Import({GraphQlConfig.class, ProductRepository.class})
class ProductControllerTest {

    @Autowired
    private GraphQlTester graphQlTester;

    @Test
    public void contextLoads() {
        assertNotNull(graphQlTester);
    }

    @Test
    void testGetAllProductsQueryReturnsOneProduct() {
        String document = """
            query {
              allProducts {
                id
                title
                desc
              }
            }   
        """;

        graphQlTester.document(document)
                .execute()
                .path("allProducts")
                .entityList(Product.class)
                .hasSizeGreaterThan(0);
    }

    @Test
    void testGetProductReturnsCorrectProduct() {
        String document = """
            query findProduct($id:ID) {
              getProduct(id:$id) {
                id
                title
                desc
              }
            } 
        """;

        graphQlTester.document(document)
                .variable("id",1)
                .execute()
                .path("getProduct")
                .entity(Product.class)
                .satisfies(product -> product.getTitle().equals("Yummy \uD83D\uDE0B Waffles"));
    }

    @Test
    void testGetProductMatchesExpectedJSON() {
        String document = """
        query findProduct($id:ID) {
          getProduct(id:$id) {
            id
            title
            desc
          }
        } 
        """;

        String expected = """
        {
            "id": "1",
            "title": "Yummy 😋 Waffles",
            "desc": "These are the best waffles ever!"
        }
        """;

        graphQlTester.document(document)
                .variable("id",1)
                .execute()
                .path("getProduct")
                .matchesJson(expected);
    }

    @Test
    void testCreateProduct() {
        String document = """
            mutation AddProduct($input: ProductInput) {
              createProduct(input:$input) {
                id
                title
                desc
              }
            }
        """;
        Map<String,Object> input = new HashMap<>();
        input.put("id",null);
        input.put("title", "My New Product Title");
        input.put("desc", "My new desc!");

        graphQlTester.document(document)
                .variable("input", input)
                .execute()
                .path("createProduct")
                .entity(Product.class)
                .satisfies(product -> {
                    assertNotNull(product.getId());
                    assertEquals("My New Product Title",product.getTitle());
                    assertEquals("My new desc!", product.getDesc());
                });
    }

    @Test
    void testUpdateProduct() {
        String document = """
            mutation AddProduct($input: ProductInput) {
              updateProduct(input:$input) {
                id
                title
                desc
              }
            }
        """;
        Map<String,Object> input = new HashMap<>();
        input.put("id",1);
        input.put("title", "Yummy 😋 Waffles");
        input.put("desc", "Voted America's #1 Waffle!");

        graphQlTester.document(document)
                .variable("input", input)
                .execute()
                .path("updateProduct")
                .entity(Product.class)
                .satisfies(product -> {
                    assertEquals(1,product.getId());
                    assertEquals("Yummy 😋 Waffles",product.getTitle());
                    assertEquals("Voted America's #1 Waffle!", product.getDesc());
                });
    }

    @Test
    void testDeleteProduct() {
        String document = """
            mutation DeleteProduct($id: Int) {
              deleteProduct(id:$id)
            }
        """;

        graphQlTester.document(document)
                .variable("id", 1)
                .executeAndVerify();
    }

}