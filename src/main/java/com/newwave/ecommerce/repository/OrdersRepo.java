package com.newwave.ecommerce.repository;

import com.newwave.ecommerce.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepo extends JpaRepository<Orders, Long> {
    Orders findOrderByOrderId(Long orderId);
    List<Orders> findAllByUsername(String username);
}
