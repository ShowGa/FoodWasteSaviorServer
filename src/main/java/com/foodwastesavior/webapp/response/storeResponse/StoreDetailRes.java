package com.foodwastesavior.webapp.response.storeResponse;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StoreDetailRes {
    private Integer storeId;
    private String storeName;
    private String coverImageUrl;
    private String logoImageUrl;
    private String about;
    private Double storeRating;
    private Integer storeRatingCount;
    // address table field
    private String storeAddress;
}
