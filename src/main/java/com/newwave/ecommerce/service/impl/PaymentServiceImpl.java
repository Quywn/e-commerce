package com.newwave.ecommerce.service.impl;

import com.newwave.ecommerce.domain.request.PaymentRequest;
import com.newwave.ecommerce.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.smartcardio.Card;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Override
    public boolean processPayment(Card card, PaymentRequest paymentRequest) {
        //update Card -> Ordered and remove Cart
        //save Payment
        return false;
    }
}
