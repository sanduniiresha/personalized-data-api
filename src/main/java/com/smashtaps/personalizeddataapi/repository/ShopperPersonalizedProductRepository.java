package com.smashtaps.personalizeddataapi.repository;

import com.smashtaps.personalizeddataapi.entity.ShopperPersonalizedProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopperPersonalizedProductRepository extends JpaRepository<ShopperPersonalizedProduct, Long> {
     @Query("SELECT p.productId, spp.relevancyScore FROM ShopperPersonalizedProduct spp " +
            "JOIN spp.product p " +
            "WHERE spp.shopper.shopperId = :shopperId " +
            "AND (:category IS NULL OR p.category = :category) " +
            "AND (:brand IS NULL OR p.brand = :brand)")
    Page<Object[]> findByShopperIdAndFilters(String shopperId, String category, String brand, Pageable pageable);

}

