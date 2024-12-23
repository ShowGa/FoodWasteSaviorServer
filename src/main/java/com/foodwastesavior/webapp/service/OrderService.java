package com.foodwastesavior.webapp.service;

import com.foodwastesavior.webapp.request.CreateOrderReq;
import com.foodwastesavior.webapp.response.orderRes.*;

import java.util.List;

public interface OrderService {
    UserContributionRes countUserContribution(Integer userId, String jwt);

    UserOrderDetailRes userCreateOrder(CreateOrderReq createOrderReq, String jwt);

    List<UserOrderList> getUserOrderList(Integer userId, String jwt);

    UserOrderDetail getUserOrderDetail(Integer orderId, String jwt);

    String completeTheOrder(Integer orderId, String jwt);

    List<UserOrderHistoryListRes> getOrderHistoryList(String jwt);

    UserOrderHistoryDetailRes getUserOrderHistoryDetail(Integer orderId, String jwt);

    // ========= MyStore ========== //
    List<MyStoreOrdersListRes> getAllWaitingForConfirmOrdersList(String jwt);

    String accepttheorder(Integer orderId, String jwt);

    List<MyStoreOrdersListRes> getAllConfirmedOrdersList(String jwt);
}
