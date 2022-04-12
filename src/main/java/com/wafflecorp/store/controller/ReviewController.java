package com.wafflecorp.store.controller;

import com.wafflecorp.store.model.Review;
import com.wafflecorp.store.repository.ReviewRepository;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ReviewController {

    private final ReviewRepository repository;

    public ReviewController(ReviewRepository repository) {
        this.repository = repository;
    }

    @QueryMapping
    public List<Review> allReviews() {
        return repository.findAll();
    }
}
