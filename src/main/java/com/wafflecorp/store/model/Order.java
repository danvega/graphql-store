package com.wafflecorp.store.model;

import java.time.LocalDate;

public class Order {

    private Integer id;
    private Product product;
    private Integer qty;
    private Customer customer;
    private LocalDate orderedOn;
    private OrderStatus status;

    public Order() {

    }

    public Order(Integer oid, Product product, Integer qty, Customer customer, LocalDate orderedOn, OrderStatus status) {
        this.id = oid;
        this.product = product;
        this.qty = qty;
        this.customer = customer;
        this.orderedOn = orderedOn;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDate getOrderedOn() {
        return orderedOn;
    }

    public void setOrderedOn(LocalDate orderedOn) {
        this.orderedOn = orderedOn;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "oid=" + id +
                ", product=" + product +
                ", qty=" + qty +
                ", customer=" + customer +
                ", orderedOn=" + orderedOn +
                ", status=" + status +
                '}';
    }
}
