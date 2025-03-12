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
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "orders_id")
    private Long orderId;
    private String username;
    @ElementCollection
    @CollectionTable(name = "orders_product_quantities", joinColumns = @JoinColumn(name = "orders_id"))
    @MapKeyJoinColumn(name = "product_id")
    @Column(name = "orders_products")
    private Map<Product, Integer> orderProducts = new HashMap<>();
    private String orderStatus;

}
