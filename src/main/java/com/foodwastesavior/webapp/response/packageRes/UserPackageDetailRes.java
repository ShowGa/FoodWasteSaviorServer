package com.foodwastesavior.webapp.response.packageRes;

import com.foodwastesavior.webapp.model.entity.Package;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalTime;

@Data
@AllArgsConstructor
public class UserPackageDetailRes {
    private Integer packageId;
    private String packageName;
    private String packageCoverImageUrl;
    private String packageDescription;
    private String packageAllergensDesc;
    private Package.Category packageCategory;
    private Integer originPrice;
    private Integer discountPrice;

    // packageSalesRule information
    private LocalTime packageStartTime;
    private LocalTime packageEndTime;
    private Integer quantityRemaining;
    private Boolean todayIsActive;

    // favorite
    private Boolean isFavorite;
}
