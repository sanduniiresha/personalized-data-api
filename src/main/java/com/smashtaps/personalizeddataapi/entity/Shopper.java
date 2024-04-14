package com.smashtaps.personalizeddataapi.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Table(name = "shopper")
@Data
public class Shopper {

    @Id
    @Column(name = "shopper_id")
    private String shopperId;

    @ManyToMany
    @JoinTable(
            name = "shopper_personalized_product",
            joinColumns = @JoinColumn(name = "shopper_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<Product> products;

}
