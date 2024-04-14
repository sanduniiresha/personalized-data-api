package com.smashtaps.personalizeddataapi.service;

import com.smashtaps.personalizeddataapi.dto.ProductShelfDTO;
import com.smashtaps.personalizeddataapi.dto.ShopperProductsResponseDTO;
import com.smashtaps.personalizeddataapi.repository.ShopperPersonalizedProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ECommerceProductService {

    @Autowired
    private ShopperPersonalizedProductRepository shopperPersonalizedProductRepository;
    public Page<ShopperProductsResponseDTO> getShopperProducts(
            String shopperId,
            String category,
            String brand,
            int page,
            int limit
    ) {
        Pageable pageable = PageRequest.of(page - 1, limit);

        Page<Object[]> resultsPage = shopperPersonalizedProductRepository
                .findByShopperIdAndFilters(shopperId, category, brand, pageable);

        List<ShopperProductsResponseDTO> responseDTOs = new ArrayList<>();

        for (Object[] result : resultsPage.getContent()) {
            String productId = (String) result[0];
            double relevancyScore = (double) result[1];

            ShopperProductsResponseDTO responseDTO = new ShopperProductsResponseDTO();

            ProductShelfDTO productShelfDTO = new ProductShelfDTO();
            productShelfDTO.setProductId(productId);
            productShelfDTO.setRelevancyScore(relevancyScore);

            List<ProductShelfDTO> products = new ArrayList<>();
            products.add(productShelfDTO);
            responseDTO.setProducts(products);

            responseDTOs.add(responseDTO);
        }
        return new PageImpl<>(responseDTOs, pageable, resultsPage.getTotalElements());
    }

}
