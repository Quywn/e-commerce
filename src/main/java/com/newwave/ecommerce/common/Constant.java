package com.newwave.ecommerce.common;

import lombok.Getter;

public class Constant {
    private static final String SAMPLE_CONSTANt = "DEMO_CONSTANT";

    @Getter
    public enum PaymentStatus {
        PROCESS("PROCESSING"),
        FAIL("FAIL"),
        SUCCESS("SUCCESS");

        private final String description;

        PaymentStatus(String description) {
            this.description = description;
        }
    }


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

}