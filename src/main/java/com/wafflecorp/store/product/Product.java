package com.wafflecorp.store.product;

import com.wafflecorp.store.order.Order;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Product {

    @Id
    @GeneratedValue
    private Integer id;
    private String title;
    @Column(name = "description")
    private String desc;
    @OneToMany(mappedBy = "product")
    private Set<Order> orders = new HashSet<>();

    public Product() {}

    public Product(String title, String desc) {
        this.title = title;
        this.desc = desc;
    }

    public Product(Integer id, String title, String desc) {
        this.id = id;
        this.title = title;
        this.desc = desc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
