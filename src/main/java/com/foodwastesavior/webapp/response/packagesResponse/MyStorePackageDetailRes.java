package com.foodwastesavior.webapp.response.packagesResponse;

import com.foodwastesavior.webapp.model.entity.Package.Category;
import com.foodwastesavior.webapp.model.entity.PackageSalesRule;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
public class MyStorePackageDetailRes {
    private Integer packageId;
    private String packageName;
    private String packageCoverImageUrl;
    private String packageDescription;
    private String packageAllergensDesc;
    private Category packageCategory;
    private Integer originPrice;
    private Integer discountPrice;
    private Boolean isActive;

}
