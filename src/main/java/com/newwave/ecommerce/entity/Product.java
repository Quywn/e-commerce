package com.newwave.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_code", nullable = false)
    private String productCode;
    @Column(name = "product_name", nullable = false)
    private String productName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category")
    @JsonIgnore
    private Category category;

    @DecimalMin(value = "0.00")
    @Column(name = "price")
    private double price;

    @Min(value = 0)
    @Column(name = "quantityStock")
    private int quantityStock;

    @Min(value = 0)
    @Column(name = "quantityOrdered")
    private int quantityOrdered;

    @Column(name = "image_url")
    private String imageUrl;
}
