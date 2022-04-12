package com.wafflecorp.store.controller;

import com.wafflecorp.store.model.Review;
import com.wafflecorp.store.repository.ReviewRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class ReviewController {

    private final ReviewRepository repository;

    public ReviewController(ReviewRepository repository) {
        this.repository = repository;
    }

    @QueryMapping
    public Iterable<Review> allReviews() {
        return repository.findAll();
    }

    @QueryMapping
    public Review getReview(@Argument Integer id) {
        Optional<Review> review = repository.findById(id);
        return review.isPresent() ? review.get() : null;
    }

}
