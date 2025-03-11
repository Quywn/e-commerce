package com.newwave.ecommerce.domain.request;

import lombok.Data;

@Data
public class PaymentRequest {
    private String username;
    private String paymentMethod;
    private String cardNumber;
    private String cardExpiry;
    private String cardCvc;
}
