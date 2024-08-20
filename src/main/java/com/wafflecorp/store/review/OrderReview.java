package com.wafflecorp.store.review;

import com.wafflecorp.store.order.Order;

public record OrderReview(Integer id, String title, String body, Integer rating, Order order) {
}
