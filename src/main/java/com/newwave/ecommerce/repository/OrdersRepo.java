package com.newwave.ecommerce.repository;

import com.newwave.ecommerce.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepo extends JpaRepository<Orders, Long> {
    Orders findOrderByOrderId(Long orderId);
}
