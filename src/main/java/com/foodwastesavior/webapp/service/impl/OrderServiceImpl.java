package com.foodwastesavior.webapp.service.impl;

import com.foodwastesavior.webapp.exception.NotFoundException;
import com.foodwastesavior.webapp.exception.TokenValidationException;
import com.foodwastesavior.webapp.model.entity.Order;
import com.foodwastesavior.webapp.model.entity.User;
import com.foodwastesavior.webapp.repository.OrderRepository;
import com.foodwastesavior.webapp.repository.UserRepository;
import com.foodwastesavior.webapp.response.orderRes.UserContributionRes;
import com.foodwastesavior.webapp.service.OrderService;
import com.foodwastesavior.webapp.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @Override
    public UserContributionRes countUserContribution(Integer userId, String jwt) {
        // verify
        String subjectInfo = JwtUtil.validateToken(jwt);

        // find userId
        User foundUser = userRepository.findByEmail(subjectInfo).orElseThrow(() -> new NotFoundException("無法根據憑證找到您的用戶資訊!"));

        if (foundUser.getUserId() != userId) {
            throw new TokenValidationException("靠!還想偷偷來啊!");
        }

        // count contribution
        Long orderAmounts = orderRepository.countUserOrderAmount(userId).orElseGet(() -> {
            return 0L;
        });

        Long savedMoney = orderRepository.countUserSavedMoney(userId).orElseGet(() -> {
            return 0L;
        });

        return new UserContributionRes(orderAmounts, savedMoney);
    }
}
