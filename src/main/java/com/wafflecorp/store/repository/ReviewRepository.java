package com.wafflecorp.store.repository;

import com.wafflecorp.store.model.Customer;
import com.wafflecorp.store.model.Product;
import com.wafflecorp.store.model.Review;
import com.wafflecorp.store.model.ReviewStatus;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;

@Repository
public class ReviewRepository implements CrudRepository<Review> {

    private List<Review> reviews = new ArrayList<>();

    @Override
    public List<Review> findAll() {
        return reviews;
    }

    @Override
    public Review findOne(Integer id) {
        return reviews.stream().filter(review -> review.getId() == id).findFirst().orElse(null);
    }

    @Override
    public Review save(Review review) {
        if(review.getId() == null) {
            OptionalInt max = reviews.stream().mapToInt(o -> o.getId()).max();
            review.setId(max.getAsInt()+1);
            reviews.add(review);
        } else {
            Review existing = findOne(review.getId());
            if(existing != null) {
                int i = reviews.indexOf(existing);
                reviews.set(i,review);
            }
        }
        return review;
    }

    @Override
    public Boolean delete(Integer id) {
        Review existing = findOne(id);
        if(existing != null) {
            int i = reviews.indexOf(existing);
            reviews.remove(reviews.get(i));
            return true;
        }
        return false;
    }

    @PostConstruct
    private void init() {
        Customer customer = new Customer(1,"Dan","Vega","danvega@gmail.com");
        Product product = new Product(1,"Yummy 😋 Waffles","These are the best waffles ever!");
        String reviewContent = "I really enjoyed the waffles and would purchase them again!";
        Review review = new Review(1,reviewContent,product,customer, ReviewStatus.PENDING);

        reviews.add(review);
    }

}
