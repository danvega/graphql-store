package com.wafflecorp.store.controller;

import com.wafflecorp.store.config.GraphQlConfig;
import com.wafflecorp.store.model.Product;
import com.wafflecorp.store.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.graphql.test.tester.GraphQlTester;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@GraphQlTest(ProductController.class)
@Import({GraphQlConfig.class})
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
        var classic = new Product(1,"Classic Waffle", "Classic Sweet Cream Waffle");
        var pecan = new Product(2,"Pecan Waffle", "Sweet Cream Waffle made with delicious Pecan Pieces");
        var chocolateChip = new Product(3,"Chocolate Chip Waffle", "Sweet Cream Waffle covered in Chocolate Chips");
        var peanutButter = new Product(4,"Peanut Butter Chip Waffle", "Sweet Cream Waffle covered in Peanut Butter Chips");
        products.addAll(List.of(classic,pecan,chocolateChip,peanutButter));
    }

    @Test
    void testGetAllProductsQueryReturnsAllProducts() {
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
    void testGetProductReturnsProduct() {
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

        Product strawberry = new Product(5,"Strawberry Waffles","Our Classic Waffle topped with Strawberries.");
        Map<String,Object> input = new HashMap<>();
        input.put("id",strawberry.getId());
        input.put("title", strawberry.getTitle());
        input.put("desc", strawberry.getDesc());

        when(productRepository.save(ArgumentMatchers.any())).thenReturn(strawberry);

        graphQlTester.document(document)
                .variable("input", input)
                .execute()
                .path("createProduct")
                .entity(Product.class)
                .satisfies(product -> {
                    assertNotNull(product.getId());
                    assertEquals("Strawberry Waffles",product.getTitle());
                    assertEquals("Our Classic Waffle topped with Strawberries.", product.getDesc());
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

        Product strawberry = new Product(5,"Strawberry Waffle","Our Classic Waffle topped with Strawberries.");
        Map<String,Object> input = new HashMap<>();
        input.put("id",strawberry.getId());
        input.put("title", strawberry.getTitle());
        input.put("desc", strawberry.getDesc());

        when(productRepository.save(ArgumentMatchers.any())).thenReturn(strawberry);

        graphQlTester.document(document)
                .variable("input", input)
                .execute()
                .path("updateProduct")
                .entity(Product.class)
                .satisfies(product -> {
                    assertNotNull(product.getId());
                    assertEquals("Strawberry Waffle",product.getTitle());
                    assertEquals("Our Classic Waffle topped with Strawberries.", product.getDesc());
                });
    }

    @Test
    void testDeleteProduct() {
        String document = """
            mutation DeleteProduct($id: Int) {
              deleteProduct(id:$id)
            }
        """;

        Mockito.doNothing().when(productRepository).deleteById(1);

        graphQlTester.document(document)
                .variable("id", 1)
                .executeAndVerify();

    }

}