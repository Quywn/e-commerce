package com.newwave.ecommerce.common;


import lombok.Getter;

public class Constant {

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

}