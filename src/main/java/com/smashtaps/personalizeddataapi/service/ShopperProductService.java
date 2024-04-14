package com.smashtaps.personalizeddataapi.service;

import com.smashtaps.personalizeddataapi.dto.ProductMetadataDTO;
import com.smashtaps.personalizeddataapi.dto.ProductShelfDTO;
import com.smashtaps.personalizeddataapi.dto.ShopperPersonalizedProductDTO;
import com.smashtaps.personalizeddataapi.entity.Product;
import com.smashtaps.personalizeddataapi.entity.Shopper;
import com.smashtaps.personalizeddataapi.entity.ShopperPersonalizedProduct;
import com.smashtaps.personalizeddataapi.repository.ProductRepository;
import com.smashtaps.personalizeddataapi.repository.ShopperPersonalizedProductRepository;
import com.smashtaps.personalizeddataapi.repository.ShopperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ShopperProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ShopperRepository shopperRepository;

    @Autowired
    ShopperPersonalizedProductRepository shopperPersonalizedProductRepository;

    public void saveShopperProductList(ShopperPersonalizedProductDTO shopperPersonalizedProductDTO) {
        String shopperId = shopperPersonalizedProductDTO.getShopperId();
        List<ProductShelfDTO> shelf = shopperPersonalizedProductDTO.getShelf();

        // Check if the Shopper exists
        Shopper shopper = shopperRepository.findByShopperId(shopperId)
                .orElseGet(() -> {
                    Shopper newShopper = new Shopper();
                    newShopper.setShopperId(shopperId);
                    return shopperRepository.save(newShopper);
                });

        for (ProductShelfDTO productShelfDTO : shelf) {
            String productId = productShelfDTO.getProductId();
            double relevancyScore = productShelfDTO.getRelevancyScore();

            Product product = productRepository.findByProductId(productId)
                    .orElseGet(() -> {
                        Product newProduct = new Product();
                        newProduct.setProductId(productId);
                        return productRepository.save(newProduct);
                    });

            ShopperPersonalizedProduct shopperProduct = new ShopperPersonalizedProduct();
            shopperProduct.setShopper(shopper);
            shopperProduct.setProduct(product);
            shopperProduct.setRelevancyScore(relevancyScore);

            try {
                shopperPersonalizedProductRepository.save(shopperProduct);
            } catch (DataIntegrityViolationException e) {
                System.out.println("Skipping existing entry for productId: " + productId);
            }
        }
    }

    public void saveProductMetadata(ProductMetadataDTO productMetadataDTO) {
        String productId = productMetadataDTO.getProductId();
        String category = productMetadataDTO.getCategory();
        String brand = productMetadataDTO.getBrand();

        Optional<Product> optionalProduct = productRepository.findByProductId(productId);
        if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();

            if (Objects.equals(existingProduct.getCategory(), category) && Objects.equals(existingProduct.getBrand(), brand)) {
                throw new IllegalArgumentException("Product metadata already exists for productId: " + productId);
            } else {
                existingProduct.setCategory(category);
                existingProduct.setBrand(brand);
                productRepository.save(existingProduct);
            }
        } else {
            Product newProduct = new Product();
            newProduct.setProductId(productId);
            newProduct.setCategory(category);
            newProduct.setBrand(brand);
            productRepository.save(newProduct);
        }
    }

}
