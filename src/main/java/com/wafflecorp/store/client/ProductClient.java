package com.wafflecorp.store.client;

import com.wafflecorp.store.ClientApp;
import com.wafflecorp.store.product.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.graphql.client.HttpSyncGraphQlClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import reactor.core.publisher.Mono;

@Component
public class ProductClient {

    private static final Logger log = LoggerFactory.getLogger(ClientApp.class);
    private final HttpSyncGraphQlClient client;

    public ProductClient(RestClient.Builder builder) {
        RestClient restClient = builder.baseUrl("http://localhost:8080/graphql").build();
        this.client = HttpSyncGraphQlClient.builder(restClient).build();
    }

    public void retrieveProductsSynchronously() {
        log.info("Synchronously Retrieving Products ========================");
        Product product = client.documentName("product")
                .variable("id", 1)
                .retrieveSync("getProduct")
                .toEntity(Product.class);
        log.info("Product {}", product);
    }

    public void retrieveProductsAsynchronously() {
        log.info("Asynchronously Retrieving Products ========================");
        Mono<Product> p2 = retrieveProductAsync(2);
        Mono<Product> p3 = retrieveProductAsync(3);
        Mono<Product> p4 = retrieveProductAsync(4);
        Mono.zip(p2, p3, p4).doOnNext(System.out::println).block();
    }

    public Mono<Product> retrieveProductAsync(int id) {
        return client.documentName("product")
                .variable("id", id)
                .retrieve("getProduct")
                .toEntity(Product.class);
    }

}
