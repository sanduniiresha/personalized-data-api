package com.smashtaps.personalizeddataapi.controller;

import com.smashtaps.personalizeddataapi.dto.ShopperProductsResponseDTO;
import com.smashtaps.personalizeddataapi.service.ECommerceProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/api/external")
public class ExternalECommerceController {

    @Autowired
    ECommerceProductService eCommerceProductService;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @GetMapping("/shoppers/{shopperId}/products")
    public ResponseEntity<Page<ShopperProductsResponseDTO>> getShopperProducts(
            @PathVariable String shopperId,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String brand,
            @RequestParam(defaultValue = "1") @Positive int page,
            @RequestParam(defaultValue = "10") @Positive @Max(value = 100) int size) {

        Page<ShopperProductsResponseDTO> productsPage = eCommerceProductService
                .getShopperProducts(shopperId, category, brand, page, size);
        if (productsPage.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(productsPage);
        }
    }

}
