package com.newwave.ecommerce.service.impl;

import com.newwave.ecommerce.dto.CartDTO;
import com.newwave.ecommerce.dto.OrderDTO;
import com.newwave.ecommerce.dto.OrderItemDTO;
import com.newwave.ecommerce.dto.ProductDTO;
import com.newwave.ecommerce.entity.Orders;
import com.newwave.ecommerce.repository.OrdersRepo;
import com.newwave.ecommerce.service.OrderDemoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderDemoService {
    private final OrdersRepo ordersRepo;

    public OrderServiceImpl(OrdersRepo ordersRepo) {
        this.ordersRepo = ordersRepo;
    }

    @Override
    public List<Orders> getOrdersByUsername(String username) {
        return ordersRepo.findAllByUsername(username);
    }

    @Override
    public OrderDTO addOrder(CartDTO cartDTO) {
        // Ghép các sản phẩm + số lượng thành chuỗi mô tả đơn hàng
        StringBuilder orderedProductBuilder = new StringBuilder();

        for (OrderItemDTO item : cartDTO.getOrderedProducts()) {
            ProductDTO product = item.getProduct();
            Integer quantity = item.getQuantity();

            orderedProductBuilder.append("Product: ")
                    .append(product.getProductName())
                    .append(", Quantity: ")
                    .append(quantity)
                    .append(" | "); // thêm dấu phân cách nếu nhiều sản phẩm
        }

        // Xoá dấu "|" cuối nếu có
        String orderedProduct = orderedProductBuilder.toString().replaceAll(" \\| $", "");

        Orders orders = new Orders();
        orders.setUsername(cartDTO.getUsername());
        orders.setOrderedProduct(orderedProduct);
        orders.setOrderStatus(cartDTO.getStatus() != null ? cartDTO.getStatus() : "PENDING");

        ordersRepo.save(orders);

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setUsername(orders.getUsername());
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
