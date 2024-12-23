package com.foodwastesavior.webapp.response.orderRes;

import com.foodwastesavior.webapp.model.entity.Order;
import com.foodwastesavior.webapp.model.entity.Package;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class UserOrderHistoryDetailRes {
        // Order Information
        private Integer orderId;
        private Order.OrderStatus orderStatus;
        private LocalDate orderDate;
        private Integer orderTotalPrice;
        private Integer orderQuantity;
        private String orderConfirmCode;

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
}
