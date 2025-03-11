package com.newwave.ecommerce.service.impl;

import com.newwave.ecommerce.repository.OrderedRepo;
import com.newwave.ecommerce.service.OrderService;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderedRepo orderedRepo;

    public OrderServiceImpl(OrderedRepo orderedRepo) {
        this.orderedRepo = orderedRepo;
    }


    @Override
    public List<Ordered> getOrderedByUsername(String username) {
        Optional<List<Ordered>> orderedList = orderedRepo.findOrdersByUsername(username);
        return orderedList.orElse(null);
    }
}
