package com.foodwastesavior.webapp.response.packageRes;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;

@Data
@AllArgsConstructor
public class StoreDetailPackageCardRes {
    private Integer packageId;
    private String packageCoverImageUrl;
    private String packageName;
    private Integer discountPrice;
}
