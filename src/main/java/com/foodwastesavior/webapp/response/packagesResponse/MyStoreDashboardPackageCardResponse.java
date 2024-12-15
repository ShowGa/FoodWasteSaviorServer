package com.foodwastesavior.webapp.response.packagesResponse;

import com.foodwastesavior.webapp.model.entity.Package.Category;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MyStoreDashboardPackageCardResponse {
    private Integer packageId;
    private String packageName;
    private String packageCoverImageUrl;
    private Category packageCategory;
}
