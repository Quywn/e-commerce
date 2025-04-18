package com.newwave.ecommerce.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private String productCode;
    @NotNull
    private String productName;
    @NotNull
    @DecimalMin(value = "0.00")
    private double price;
    private CategoryDTO category;
    @Min(value = 0)
    private int quantityStock;
    @Min(value = 0)
    private int quantityOrdered;
    private String imageUrl;


}
