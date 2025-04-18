package com.newwave.ecommerce.dto.response;

import lombok.Data;

@Data
public class PaymentResponse {
    private String paymentId;
    private String paymentStatus;
}
