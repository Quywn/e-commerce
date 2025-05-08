package com.newwave.ecommerce.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class CartDTO {
    private Long cartId;
    private List<OrderItemDTO> orderedProducts;
    private String username;
    private String status;
}
