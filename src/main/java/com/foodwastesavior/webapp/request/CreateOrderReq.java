package com.foodwastesavior.webapp.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateOrderReq {
    private Integer userId;
    private Integer packageId;
    private Integer quantity;
}
