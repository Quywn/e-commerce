package com.newwave.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ordered_id")
    private Long orderId;
    private String username;
    @ElementCollection
    @CollectionTable(name = "ordered_product_quantities", joinColumns = @JoinColumn(name = "ordered_id"))
    @MapKeyJoinColumn(name = "product_id")
    @Column(name = "ordered_products")
    private Map<Product, Integer> orderProducts = new HashMap<>();

}
