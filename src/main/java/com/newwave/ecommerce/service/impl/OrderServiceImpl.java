package com.newwave.ecommerce.service.impl;

import com.newwave.ecommerce.domain.CartDTO;
import com.newwave.ecommerce.domain.OrderDTO;
import com.newwave.ecommerce.domain.ProductDTO;
import com.newwave.ecommerce.entity.Category;
import com.newwave.ecommerce.entity.Orders;
import com.newwave.ecommerce.entity.Product;
import com.newwave.ecommerce.exception.NotFoundException;
import com.newwave.ecommerce.repository.OrdersRepo;
import com.newwave.ecommerce.service.OrderDemoService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderDemoService {
    private final OrdersRepo ordersRepo;

    public OrderServiceImpl(OrdersRepo ordersRepo) {
        this.ordersRepo = ordersRepo;
    }

    @Override
    public List<Orders> getOrderedByUsername(String username) {
        return null;
    }

    @Override
    public OrderDTO addOrder(CartDTO cartDTO) {
        Map<Product, Integer> productIntegerMap = new HashMap<>(Map.of());
        cartDTO.getOrderedProducts().forEach((productdto, value) -> {
            productIntegerMap.put(buildProduct(productdto), value);
        });
        Orders orders = new Orders();
        orders.setUsername(cartDTO.getUsername());
        orders.setOrderProducts(productIntegerMap);
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

    private Product buildProduct(ProductDTO productDTO) {
        return Product.builder()
                .productCode(productDTO.getProductCode())
                .productName(productDTO.getProductName())
                .quantityStock(productDTO.getQuantityStock())
                .price(productDTO.getPrice())
                .imageUrl(productDTO.getImageUrl())
                .build();
    }


}
