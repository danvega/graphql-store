package com.wafflecorp.store;

import com.wafflecorp.store.client.ProductClient;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.web.client.RestClientAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Import;

@Import({RestClientAutoConfiguration.class, ProductClient.class})
public class ClientApp implements ApplicationRunner {

    private final ProductClient productClient;

    public ClientApp(ProductClient productClient) {
        this.productClient = productClient;
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(ClientApp.class).web(WebApplicationType.NONE).run(args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        productClient.retrieveProductsSynchronously();
        productClient.retrieveProductsAsynchronously();
    }

}
