package com.newwave.ecommerce.dto;

import lombok.Data;
import java.util.Map;

@Data
public class CartDTO {
    private Long cartId;
    private Map<ProductDTO, Integer> orderedProducts;
    private String username;
    private String status;
}
