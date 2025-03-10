package com.newwave.ecommerce.service.impl;

import com.newwave.ecommerce.service.OrderService;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Override
    public List<Ordered> getOrderedByUsername(String username) {
        return List.of();
    }
}
