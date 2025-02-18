package com.newwave.ecommerce.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
public class Cart implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Long cardId;
    private Long userId;
    private User user;
    //
}
