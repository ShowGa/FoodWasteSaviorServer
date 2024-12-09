package com.foodwastesavior.webapp.service.impl;

import com.foodwastesavior.webapp.model.entity.Order;
import com.foodwastesavior.webapp.repository.OrderRepository;
import com.foodwastesavior.webapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
}
