package com.newwave.ecommerce.domain;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.constraints.Min;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class CartDTO {
    private Long cartId;

    private Map<ProductDTO, Integer> orderedProducts;

    private String username;

}
