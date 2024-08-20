package com.wafflecorp.store.order;

import com.wafflecorp.store.customer.Customer;
import com.wafflecorp.store.product.Product;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue
    private Integer id;
    private Integer qty;
    private LocalDate orderedOn;
    private OrderStatus status;
    @ManyToOne
    private Product product;
    @ManyToOne
    private Customer customer;

    public Order() {}

    public Order(Integer qty, LocalDate orderedOn, OrderStatus status, Product product, Customer customer) {
        this.qty = qty;
        this.orderedOn = orderedOn;
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

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
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
        return "Order{" +
                "id=" + id +
                ", qty=" + qty +
                ", orderedOn=" + orderedOn +
                ", status=" + status +
                ", product=" + product +
                ", customer=" + customer +
                '}';
    }
}
