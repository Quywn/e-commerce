package com.newwave.ecommerce.service.impl;

import com.newwave.ecommerce.common.Constant;
import com.newwave.ecommerce.common.Utils;
import com.newwave.ecommerce.domain.request.PaymentRequest;
import com.newwave.ecommerce.entity.Cart;
import com.newwave.ecommerce.entity.Order;
import com.newwave.ecommerce.entity.Payment;
import com.newwave.ecommerce.exception.NotFoundException;
import com.newwave.ecommerce.repository.CartRepo;
import com.newwave.ecommerce.repository.OrderedRepo;
import com.newwave.ecommerce.repository.PaymentRepo;
import com.newwave.ecommerce.service.PaymentService;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final CartRepo cartRepo;
    private final PaymentRepo paymentRepo;
    private final OrderedRepo orderedRepo;
    private final CartServiceImpl cartService;
    private final CartServiceImpl cartServiceImpl;
    Utils utils;


    public PaymentServiceImpl(CartRepo cartRepo, PaymentRepo paymentRepo, OrderedRepo orderedRepo, CartServiceImpl cartService, CartServiceImpl cartServiceImpl) {
        this.cartRepo = cartRepo;
        this.paymentRepo = paymentRepo;
        this.orderedRepo = orderedRepo;
        this.cartService = cartService;
        this.cartServiceImpl = cartServiceImpl;
    }


    @Override
    public boolean processPayment(PaymentRequest paymentRequest) {
        Optional<Cart> cart = cartRepo.findCartByUsername(paymentRequest.getUsername());
        if (cart.isEmpty()) {
            throw new NotFoundException("No cart found for username " + paymentRequest.getUsername());
        }

        Order order = new Order();
        order.setUsername(cart.get().getUsername());
        order.setOrderId(cart.get().getCartId());
        order.setOrderProducts(cart.get().getOrderProducts());
        orderedRepo.save(order);

        Payment payment = new Payment();
        payment.setAmount(cartService.getCartTotal(cart.get()));
        payment.setPaymentMethod(paymentRequest.getPaymentMethod());
        payment.setOrderId(order.getOrderId());

        //todo: call third-payment-service, handler STATUS: PROCESS + FAIL
        payment.setStatus(Constant.PaymentStatus.SUCCESS.getDescription());
        payment.setPaymentDate(utils.getToday());
        paymentRepo.save(payment);
        cartServiceImpl.clearCartByUser(cart.get().getUsername());
        return true;
    }
}
