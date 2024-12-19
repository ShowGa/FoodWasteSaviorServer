package com.foodwastesavior.webapp.response.packageRes;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;

@Data
@AllArgsConstructor
public class FavoriteCardRes {
    // package table
    private Integer packageId;
    private String packageName;
    private String packageCoverImageUrl;
    private Integer packageDiscountPrice;
    // store table
    private String storeName;
    private String storeLogoImageUrl;
    // packagesalesrule
    private LocalTime pickupStartTime;
    private LocalTime pickupEndTime;
    private Boolean isActive;

}
