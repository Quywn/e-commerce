package com.newwave.ecommerce.service;

import com.newwave.ecommerce.domain.request.PaymentRequest;

public interface PaymentService {
    boolean processPayment(PaymentRequest paymentRequest);
}
