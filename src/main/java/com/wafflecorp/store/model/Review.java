package com.wafflecorp.store.model;

public class Review {

    private Integer id;
    private String content;
    private Product product;
    private Customer customer;
    private ReviewStatus status;

    public Review(Integer id, String content, Product product, Customer customer, ReviewStatus status) {
        this.id = id;
        this.content = content;
        this.product = product;
        this.customer = customer;
        this.status = status;
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

    public ReviewStatus getStatus() {
        return status;
    }

    public void setStatus(ReviewStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", product=" + product +
                ", customer=" + customer +
                ", status=" + status +
                '}';
    }
}
