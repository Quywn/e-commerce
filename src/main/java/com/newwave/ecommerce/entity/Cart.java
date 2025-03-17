package com.newwave.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(name = "cart_id")
    private Long cartId;

    @Column(name = "user_name", nullable = false)
    private String username;

    @ElementCollection
    @CollectionTable(name = "cart_product_quantities", joinColumns = @JoinColumn(name = "cart_id"))
    @MapKeyJoinColumn(name = "product_id")
    @Column(name = "order_products")
    @JsonIgnore
    private Map<Product, Integer> orderProducts = new HashMap<>();
}
