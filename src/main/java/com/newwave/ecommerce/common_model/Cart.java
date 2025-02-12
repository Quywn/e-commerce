package com.newwave.ecommerce.common_model;

import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@Data
@Builder
public class Cart implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Integer cardId;
    private Long userId;
    private User user;
    private Set<Order> orderList;
}
