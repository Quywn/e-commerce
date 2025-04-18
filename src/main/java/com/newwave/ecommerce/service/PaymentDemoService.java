package com.newwave.ecommerce.service;

import com.newwave.ecommerce.dto.request.PaymentRequest;
import com.newwave.ecommerce.dto.response.PaymentResponse;

public interface PaymentDemoService {
    PaymentResponse processPayment(PaymentRequest paymentRequest);
}
