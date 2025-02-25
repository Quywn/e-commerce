package com.newwave.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cart implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    private String username;

    @ElementCollection
    @CollectionTable(name = "cart_product_quantities", joinColumns = @JoinColumn(name = "cartId"))
    @MapKeyJoinColumn(name = "productId")
    @Column(name = "orderProducts")
    private Map<Product, Integer> orderProducts = new HashMap<>();
}
