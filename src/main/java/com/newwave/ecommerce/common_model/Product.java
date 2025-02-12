package com.newwave.ecommerce.common_model;

import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
public class Product implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Integer productId;
    private String productName;
    private double price;
    private String category;
    private int quantity;
    private String imageUrl;

}
