package com.smashtaps.personalizeddataapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ShopperProductsResponseDTO {

    private List<ProductShelfDTO> products;

}
