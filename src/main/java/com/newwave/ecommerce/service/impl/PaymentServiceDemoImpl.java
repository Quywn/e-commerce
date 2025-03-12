package com.newwave.ecommerce.service.impl;

import com.newwave.ecommerce.common.Constant;
import com.newwave.ecommerce.domain.request.PaymentRequest;
import com.newwave.ecommerce.domain.response.PaymentResponse;
import com.newwave.ecommerce.service.PaymentDemoService;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PaymentServiceDemoImpl implements PaymentDemoService {

    @Override
    public PaymentResponse processPayment(PaymentRequest paymentRequest) {
        PaymentResponse paymentResponse = new PaymentResponse();
        Random random = new Random();
        switch (random.nextInt(3)) {
            case 0:
                paymentResponse.setPaymentStatus(Constant.PaymentStatus.SUCCESS.getDescription());
                break;
            case 1:
                paymentResponse.setPaymentStatus(Constant.PaymentStatus.PROCESS.getDescription());
                break;
            case 2:
                paymentResponse.setPaymentStatus(Constant.PaymentStatus.FAIL.getDescription());
                break;
        }
        return paymentResponse;
    }
}
