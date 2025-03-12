package com.newwave.ecommerce.domain.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentRequest {
    private String username;
    private Double paymentAmount;
}
