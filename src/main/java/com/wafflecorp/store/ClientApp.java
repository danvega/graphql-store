package com.wafflecorp.store;

import com.wafflecorp.store.product.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.web.client.RestClientAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Import;
import org.springframework.graphql.client.HttpSyncGraphQlClient;
import org.springframework.web.client.RestClient;
import reactor.core.publisher.Mono;

@Import(RestClientAutoConfiguration.class)
public class ClientApp implements ApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(ClientApp.class);
    private final HttpSyncGraphQlClient client;

    public ClientApp(RestClient.Builder builder) {
        RestClient restClient = builder.baseUrl("http://localhost:8080/graphql").build();
        this.client = HttpSyncGraphQlClient.builder(restClient).build();
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(ClientApp.class).web(WebApplicationType.NONE).run(args);
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {

        log.info("Synchronously Retrieving Products ========================");
        Product product = client.documentName("product")
                .variable("id", 1)
                .retrieveSync("getProduct")
                .toEntity(Product.class);
        log.info("Product {}",product);


        log.info("Asynchronously Retrieving Products ========================");
        Mono<Product> p2 = client.documentName("product")
                .variable("id", 2)
                .retrieve("getProduct")
                .toEntity(Product.class);
        Mono<Product> p3 = client.documentName("product")
                .variable("id", 3)
                .retrieve("getProduct")
                .toEntity(Product.class);
        Mono<Product> p4 = client.documentName("product")
                .variable("id", 4)
                .retrieve("getProduct")
                .toEntity(Product.class);

        Mono.zip(p2,p3, p4).doOnNext(System.out::println).block();
    }
}
