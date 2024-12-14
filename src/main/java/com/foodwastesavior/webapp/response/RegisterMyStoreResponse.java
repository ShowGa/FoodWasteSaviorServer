package com.foodwastesavior.webapp.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterMyStoreResponse {
    private String jwt;

    private Integer storeId;
    private String storeName;
    private String logoImageUrl;

}
