package com.newwave.ecommerce.domain;

import com.newwave.ecommerce.entity.Product;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class CartDTO {
    private Long cartId;

    private Map<Product, Integer> orderedProducts;

    private String username;

}
