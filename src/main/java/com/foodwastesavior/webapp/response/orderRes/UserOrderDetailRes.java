package com.foodwastesavior.webapp.response.orderRes;

import com.foodwastesavior.webapp.model.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
public class UserOrderDetailRes {
    private Integer orderId;
    private Order.OrderStatus orderStatus;
    private Integer quantity;
    private Integer orderTotalPrice;
    private String confirmationCode;
    private LocalDate orderDate;
    // store table
    private String storeName;
    private String storeLogoImageUrl;
    // address table
    private String storeAddress;
    // packageSalesRule table
    private LocalTime pickupStartTime;
    private LocalTime pickupEndTime;
}
