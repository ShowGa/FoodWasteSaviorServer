package com.foodwastesavior.webapp.response.packagesResponse;

import com.foodwastesavior.webapp.model.entity.Package.Category;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PackageDetailRes {
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
