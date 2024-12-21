package com.foodwastesavior.webapp.service;

import com.foodwastesavior.webapp.model.entity.Order;
import com.foodwastesavior.webapp.request.CreateOrderReq;
import com.foodwastesavior.webapp.response.orderRes.UserContributionRes;
import com.foodwastesavior.webapp.response.orderRes.UserOrderDetail;
import com.foodwastesavior.webapp.response.orderRes.UserOrderDetailRes;
import com.foodwastesavior.webapp.response.orderRes.UserOrderList;

import java.util.List;

public interface OrderService {
    UserContributionRes countUserContribution(Integer userId, String jwt);

    UserOrderDetailRes userCreateOrder(CreateOrderReq createOrderReq, String jwt);

    List<UserOrderList> getUserOrderList(Integer userId, String jwt);

    UserOrderDetail getUserOrderDetail(Integer orderId, String jwt);
}
