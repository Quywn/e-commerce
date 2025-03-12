package com.newwave.ecommerce.domain.response;

import lombok.Data;

@Data
public class PaymentResponse {
    private String paymentId;
    private String paymentStatus;
}
