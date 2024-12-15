package com.foodwastesavior.webapp.response.packagesResponse;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;

@Data
@AllArgsConstructor
public class PackageSalesRulesRes {

    private Integer rulesId;
    private int weekday;
    private int quantity;
    private LocalTime pickupStartTime;
    private LocalTime pickupEndTime;
    private Boolean isActive;

}
