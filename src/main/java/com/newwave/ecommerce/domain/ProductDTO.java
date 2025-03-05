package com.newwave.ecommerce.domain;

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
    @NotNull
    private String productName;
    @NotNull
    @DecimalMin(value = "0.00")
    private double price;
    @Min(value = 0)
    private int quantityStock;
    @Min(value = 0)
    private int quantityOrdered;
    private String imageUrl;


}
