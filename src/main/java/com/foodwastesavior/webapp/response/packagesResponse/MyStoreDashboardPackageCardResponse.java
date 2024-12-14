package com.foodwastesavior.webapp.response.packagesResponse;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MyStoreDashboardPackageCardResponse {
    private Integer packageId;
    private String packageName;
}
