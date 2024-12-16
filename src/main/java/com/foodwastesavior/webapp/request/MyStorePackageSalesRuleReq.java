package com.foodwastesavior.webapp.request;

import lombok.Data;

import java.time.LocalTime;

@Data
public class MyStorePackageSalesRuleReq {

    private Integer rulesId;
    private int weekday;
    private int quantity;
    private LocalTime pickupStartTime;
    private LocalTime pickupEndTime;
    private Boolean isActive;

}
