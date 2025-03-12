package com.newwave.ecommerce.service;

import com.newwave.ecommerce.domain.request.PaymentRequest;
import com.newwave.ecommerce.domain.response.PaymentResponse;

public interface PaymentDemoService {
    PaymentResponse processPayment(PaymentRequest paymentRequest);
}
