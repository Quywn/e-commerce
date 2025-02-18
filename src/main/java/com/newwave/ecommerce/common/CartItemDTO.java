package com.newwave.ecommerce.common;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class CartItemDTO {
    @NotNull
    private Integer productId;

    private String productName;

    private Double price;

    @Min(1)
    //todo: check xem @Min(0) hợp lý hơn k
    private Integer quantity;
}
