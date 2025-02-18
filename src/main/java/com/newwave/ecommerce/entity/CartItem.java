package com.newwave.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Entity
public class CartItem implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItemId;

    @OneToOne
    @JsonIgnoreProperties(value={
            "productId",
            "quantity"

    })
    private Product cartProduct;

    //số lượng sản phẩm cần mua trong giỏ hàng
    private Integer cartItemQuantity;

}
