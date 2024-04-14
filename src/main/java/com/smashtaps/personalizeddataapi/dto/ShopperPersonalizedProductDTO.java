package com.smashtaps.personalizeddataapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShopperPersonalizedProductDTO {

    @NotNull(message = "Shopper ID must not be null")
    @NotBlank(message = "Shopper ID must not be blank")
    private String shopperId;

    private List<ProductShelfDTO> shelf;

}

