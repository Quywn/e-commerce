package com.newwave.ecommerce.service.impl;

import com.newwave.ecommerce.dto.CartDTO;
import com.newwave.ecommerce.dto.OrderDTO;
import com.newwave.ecommerce.dto.OrderItemDTO;
import com.newwave.ecommerce.dto.ProductDTO;
import com.newwave.ecommerce.entity.OrderItem;
import com.newwave.ecommerce.entity.Orders;
import com.newwave.ecommerce.repository.OrdersRepo;
import com.newwave.ecommerce.service.OrderDemoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderDemoService {
    private final OrdersRepo ordersRepo;

    public OrderServiceImpl(OrdersRepo ordersRepo) {
        this.ordersRepo = ordersRepo;
    }

    @Override
    public List<OrderDTO> getOrdersByUsername(String username) {
        List<Orders> orders = ordersRepo.findAllByUsername(username);

        List<OrderDTO> orderDTOs = orders.stream().map(order -> {
            Map<ProductDTO, Integer> map = order.getItems().stream().collect(Collectors.toMap(
                    item -> new ProductDTO(item.getProductName(), item.getPrice()),
                    OrderItem::getQuantity
            ));

            return OrderDTO.builder()
                    .orderId(order.getOrderId())
                    .username(order.getUsername())
                    .orderedProducts(map)
                    .build();
        }).collect(Collectors.toList());
        return orderDTOs;
    }

    @Override
    public OrderDTO addOrder(CartDTO cartDTO) {
        Orders order = new Orders();
        order.setUsername(cartDTO.getUsername());
        order.setOrderStatus(cartDTO.getStatus() != null ? cartDTO.getStatus() : "PENDING");

        List<OrderItem> items = new ArrayList<>();
        Map<ProductDTO, Integer> orderedProductMap = new HashMap<>();

        for (OrderItemDTO itemDTO : cartDTO.getOrderedProducts()) {
            ProductDTO product = itemDTO.getProduct();
            Integer quantity = itemDTO.getQuantity();

            OrderItem item = new OrderItem();
            item.setProductName(product.getProductName());
            item.setPrice(product.getPrice());
            item.setQuantity(quantity);
            item.setOrder(order);

            items.add(item);
            orderedProductMap.put(product, quantity);
        }

        order.setItems(items);
        ordersRepo.save(order);

        return OrderDTO.builder()
                .orderId(order.getOrderId())
                .username(order.getUsername())
                .orderedProducts(orderedProductMap)
                .build();
    }


    @Override
    public boolean updateStatus(String status, Long orderId) {
        Orders orders = ordersRepo.findOrderByOrderId(orderId);
        orders.setOrderStatus(status);
        ordersRepo.save(orders);
        return true;
    }
}
