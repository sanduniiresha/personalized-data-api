package com.smashtaps.personalizeddataapi.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "shopper_personalized_product")

@Data
public class ShopperPersonalizedProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "shopper_id")
    private Shopper shopper;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "relevancy_score") // check here if it is a column or join colum
    private double relevancyScore;

}
