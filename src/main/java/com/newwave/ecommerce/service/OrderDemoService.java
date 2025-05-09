package com.newwave.ecommerce.service;

import com.newwave.ecommerce.dto.CartDTO;
import com.newwave.ecommerce.dto.OrderDTO;
import com.newwave.ecommerce.entity.Orders;
import java.util.List;

public interface OrderDemoService {
    List<OrderDTO> getOrdersByUsername(String username);
    OrderDTO addOrder(CartDTO cartDTO);
    boolean updateStatus(String status, Long orderId);
}
