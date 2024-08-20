package com.wafflecorp.store.search;

import com.wafflecorp.store.customer.Customer;
import com.wafflecorp.store.customer.CustomerRepository;
import com.wafflecorp.store.product.Product;
import com.wafflecorp.store.product.ProductRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class SearchSource {

    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;

    SearchSource(ProductRepository productRepository, CustomerRepository customerRepository) {
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
        ;
    }

    public List<Object> search(@Argument String text) {
        List<Object> result = new ArrayList<>();
        result.addAll(productRepository.findAllByTitleContaining(text));
        result.addAll(customerRepository.findAllByFirstNameOrLastName(text,text));
        result.sort(new SearchItemComparator());
        return result;
    }


    private static class SearchItemComparator implements Comparator<Object> {

        @Override
        public int compare(Object o1, Object o2) {
            return getComparisonString(o1).compareTo(getComparisonString(o2));
        }

        private static String getComparisonString(Object o) {
            return switch (o) {
                case Product product -> product.getTitle();
                case Customer customer -> customer.getFullName();
                case null, default -> throw new IllegalArgumentException();
            };
        }
    }

}
