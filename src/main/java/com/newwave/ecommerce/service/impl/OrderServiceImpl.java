package com.newwave.ecommerce.service.impl;

import com.newwave.ecommerce.dto.CartDTO;
import com.newwave.ecommerce.dto.OrderDTO;
import com.newwave.ecommerce.dto.ProductDTO;
import com.newwave.ecommerce.entity.Orders;
import com.newwave.ecommerce.repository.OrdersRepo;
import com.newwave.ecommerce.service.OrderDemoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderDemoService {
    private final OrdersRepo ordersRepo;

    public OrderServiceImpl(OrdersRepo ordersRepo) {
        this.ordersRepo = ordersRepo;
    }

    @Override
    public List<Orders> getOrderedByUsername(String username) {
        return ordersRepo.findAllByUsername(username);
    }

    @Override
    public OrderDTO addOrder(CartDTO cartDTO) {
        String orderedProduct = "";
        for (Map.Entry<ProductDTO, Integer> entry : cartDTO.getOrderedProducts().entrySet()) {
            ProductDTO product = entry.getKey();
            Integer quantity = entry.getValue();
            orderedProduct= "Product:" + product.getProductName() + ", Quantity: " + quantity;
        }

        Orders orders = new Orders();
        orders.setUsername(cartDTO.getUsername());
        orders.setOrderedProduct(orderedProduct);
        orders.setUsername(cartDTO.getUsername());
        ordersRepo.save(orders);

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setUsername(cartDTO.getUsername());
        orderDTO.setOrderId(orders.getOrderId());
        return orderDTO;
    }

    @Override
    public boolean updateStatus(String status, Long orderId) {
        Orders orders = ordersRepo.findOrderByOrderId(orderId);
        orders.setOrderStatus(status);
        ordersRepo.save(orders);
        return true;
    }
}
