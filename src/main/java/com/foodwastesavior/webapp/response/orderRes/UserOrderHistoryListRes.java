package com.foodwastesavior.webapp.response.orderRes;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class UserOrderHistoryListRes {
    private Integer orderId;
    private LocalDate orderDate;
    // store table
    private String storeName;
    private String storeLogoImageUrl;
}
