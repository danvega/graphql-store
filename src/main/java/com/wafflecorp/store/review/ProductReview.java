package com.wafflecorp.store.review;

import com.wafflecorp.store.product.Product;

public record ProductReview(Integer id, String title, String body, Integer rating, Product product) implements Review {

}
