package com.newwave.ecommerce.service;

import com.newwave.ecommerce.domain.request.PaymentRequest;

import javax.smartcardio.Card;

public interface PaymentService {
    boolean processPayment(PaymentRequest paymentRequest);
}
