package com.smashtaps.personalizeddataapi.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Table(name = "product")
@Data
public class Product {

    @Id
    @Column(name = "product_id")
    private String productId;

    @Column(name = "category")
    private String category;

    @Column(name = "brand")
    private String brand;

    @ManyToMany(mappedBy = "products")
    private Set<Shopper> shoppers;

}
