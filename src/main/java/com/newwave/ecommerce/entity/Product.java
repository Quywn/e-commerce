package com.newwave.ecommerce.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "product_name", nullable = false)
    private String productName;

    @DecimalMin(value = "0.00")
    @Column(name = "price")
    private double price;

    @NotNull
    @Min(value = 0)
    @Column(name = "quantity")
    private int quantity;

    @Column(name = "image_url")
    private String imageUrl;
}
