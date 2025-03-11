package com.newwave.ecommerce.domain;

import com.newwave.ecommerce.entity.Product;
import lombok.Data;
import java.util.Map;

@Data
public class CartDTO {
    private Long cartId;

    private Map<Product, Integer> orderedProducts;

    private String username;

}
