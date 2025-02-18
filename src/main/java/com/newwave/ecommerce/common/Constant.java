package com.newwave.ecommerce.common;

import lombok.Getter;

public class Constant {
//    public static final String JWT_SECRET = "secret";

    @Getter
    public enum Rating {
        ONE(1),
        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5);

        private final int value;

        Rating(int value) {
            this.value = value;
        }

    }

    public enum OrderStatus {
        PENDING,
        COMPLETED,
        CANCELLED;
    }
}

