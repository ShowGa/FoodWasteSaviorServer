package com.foodwastesavior.webapp.response.orderRes;

import com.foodwastesavior.webapp.model.entity.Order;
import com.foodwastesavior.webapp.model.entity.Package;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
public class UserOrderDetail {
    // Order Information
    private Integer orderId;
    private Order.OrderStatus orderStatus;
    private LocalDate orderDate;
    private Integer orderTotalPrice;
    private Integer orderQuantity;

    // Store Information
    private Integer storeId;
    private String storeLogo;
    private String storeName;

    // Address Information
    private String storeAddress;
    private Double latitude;
    private Double longitude;

    // Package Information
    private String packageName;
    private Package.Category packageCategory;

    // Package Sales Rules
    private LocalTime pickupStartTime;
    private LocalTime pickupEndTime;
}
