package com.wafflecorp.store.model;

import javax.persistence.*;

@Entity
public class Review {

    @Id
    @GeneratedValue
    private Integer id;
    private String content;
    private ReviewStatus status;
    @ManyToOne
    private Product product;
    @ManyToOne
    private Customer customer;

    public Review() {}

    public Review(String content, ReviewStatus status, Product product, Customer customer) {
        this.content = content;
        this.status = status;
        this.product = product;
        this.customer = customer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ReviewStatus getStatus() {
        return status;
    }

    public void setStatus(ReviewStatus status) {
        this.status = status;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", status=" + status +
                ", product=" + product +
                ", customer=" + customer +
                '}';
    }
}
