package com.smashtaps.personalizeddataapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductMetadataDTO {

    @NotNull(message = "Product ID must not be null")
    @NotBlank(message = "Product ID must not be blank")
    private String productId;

    private String category;

    private String brand;

}
