package com.smashtaps.personalizeddataapi.controller;

import com.smashtaps.personalizeddataapi.dto.ProductMetadataDTO;
import com.smashtaps.personalizeddataapi.dto.ShopperPersonalizedProductDTO;
import com.smashtaps.personalizeddataapi.service.ShopperProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/internal")
public class InternalDataTeamController {

    @Autowired
    ShopperProductService shopperProductService;

    @PostMapping(path = "/shoppers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveShopperProductList(@Validated @RequestBody ShopperPersonalizedProductDTO shopperPersonalizedProductDTO) {
        shopperProductService.saveShopperProductList(shopperPersonalizedProductDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Shopper Personalized Products Saved Successfully");
    }

    @PostMapping(path = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveProductMetadata(@Validated @RequestBody ProductMetadataDTO productMetadataDTO) {
        shopperProductService.saveProductMetadata(productMetadataDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Product Metadata Saved Successfully");
    }

}
