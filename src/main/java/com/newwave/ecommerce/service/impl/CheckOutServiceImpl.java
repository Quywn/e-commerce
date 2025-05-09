package com.newwave.ecommerce.service.impl;

import com.newwave.ecommerce.dto.CartDTO;
import com.newwave.ecommerce.dto.OrderDTO;
import com.newwave.ecommerce.dto.request.PaymentRequest;
import com.newwave.ecommerce.dto.response.PaymentResponse;
import com.newwave.ecommerce.service.CheckOutService;
import com.newwave.ecommerce.service.PaymentDemoService;
import org.springframework.stereotype.Service;


import static com.newwave.ecommerce.common.Constant.*;


@Service
public class CheckOutServiceImpl implements CheckOutService {
    private final CartServiceImpl cartService;
    private final PaymentDemoService paymentDemoService;
    private final OrderServiceImpl orderService;

    public CheckOutServiceImpl(CartServiceImpl cartService, PaymentDemoService paymentDemoService, OrderServiceImpl orderService) {
        this.cartService = cartService;
        this.paymentDemoService = paymentDemoService;
        this.orderService = orderService;
    }

    @Override
    public String checkOutCart(String username) {
        CartDTO cart = cartService.getCartByUser(username);

        PaymentRequest paymentRequest = PaymentRequest.builder()
                .username(username)
                .paymentAmount(cartService.getCartTotal(cart))
                .build();

        OrderDTO orderDTO = orderService.addOrder(cart);
        cartService.clearCartByUser(username);

        PaymentResponse paymentResponse = paymentDemoService.processPayment(paymentRequest);

        return switch (paymentResponse.getPaymentStatus()) {
            case PROCESS -> {
                orderService.updateStatus(PROCESS, orderDTO.getOrderId());
                yield "Payment is processing";
            }
            case FAIL -> {
                orderService.updateStatus(FAIL, orderDTO.getOrderId());
                yield "Payment failed";
            }
            case SUCCESS -> {
                orderService.updateStatus(SUCCESS, orderDTO.getOrderId());
                yield "Payment successful";
            }
            default -> null;
        };
    }
}