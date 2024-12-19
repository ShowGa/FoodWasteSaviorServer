package com.foodwastesavior.webapp.service;

import com.foodwastesavior.webapp.model.entity.Order;
import com.foodwastesavior.webapp.response.orderRes.UserContributionRes;

import java.util.List;

public interface OrderService {
    UserContributionRes countUserContribution(Integer userId, String jwt);
}
