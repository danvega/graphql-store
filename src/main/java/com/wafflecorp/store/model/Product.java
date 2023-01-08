package com.wafflecorp.store.model;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.OneToMany;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Product {

    @Id
    @GeneratedValue
    private Integer id;
    private String title;
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

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
