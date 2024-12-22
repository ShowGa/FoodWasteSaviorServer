package com.foodwastesavior.webapp.response.orderRes;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MyStoreOrdersListRes {
    private Integer orderId;
    private String orderConfirmCode;
    private Integer orderQuantity;
    // package table
    private String packageName;
    private String packageCoverImageUrl;
}
