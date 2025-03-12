package com.newwave.ecommerce.service;

import com.newwave.ecommerce.domain.CartDTO;
import com.newwave.ecommerce.domain.OrderDTO;
import com.newwave.ecommerce.entity.Orders;
import java.util.List;

public interface OrderDemoService {
    List<Orders> getOrderedByUsername(String username);
    OrderDTO addOrder(CartDTO cartDTO);
    boolean updateStatus(String status, Long orderId);
}
