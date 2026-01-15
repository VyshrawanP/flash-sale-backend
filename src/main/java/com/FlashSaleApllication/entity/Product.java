package com.FlashSaleApllication.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    //Things which we want in the database
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer stock;

    //These are getters and setters for the product
    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    // getters & setters
    public Long getId() { return id; }
    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }
}
