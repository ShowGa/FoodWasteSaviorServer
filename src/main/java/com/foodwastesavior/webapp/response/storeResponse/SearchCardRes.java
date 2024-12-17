package com.foodwastesavior.webapp.response.storeResponse;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchCardRes {
    private Integer storeId;
    private String storeCoverImage;
    private String storeName;
    private String storeAddress;
    private Double longitude;
    private Double latitude;
    private Double storeRating;
    private Double storeDistance;

}
