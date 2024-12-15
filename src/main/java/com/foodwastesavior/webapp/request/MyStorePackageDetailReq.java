package com.foodwastesavior.webapp.request;

import com.foodwastesavior.webapp.model.entity.Package;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MyStorePackageDetailReq {
    private String packageName;
    private String packageCoverImageUrl;
    private String packageDescription;
    private String packageAllergensDesc;
    private Package.Category packageCategory;
    private Integer originPrice;
    private Integer discountPrice;
    private Boolean isActive;
}
