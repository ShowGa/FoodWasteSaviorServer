package com.foodwastesavior.webapp.response.orderRes;

import com.foodwastesavior.webapp.model.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;

@Data
@AllArgsConstructor
public class UserOrderList {
    private Integer orderId;
    private Order.OrderStatus orderStatus;
    // packageSalesRule
    private LocalTime pickupStartTime;
    private LocalTime pickupEndTime;
    // store table
    private String storeLogoImageUrl;
    private String storeName;
}
