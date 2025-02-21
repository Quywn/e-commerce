package com.newwave.ecommerce.domain;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class CartItemDTO {

    private String productName;

    private Double price;

    @Min(1)
    //todo: check xem @Min(0) hợp lý hơn k
    private Integer quantity;
}
