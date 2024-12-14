package com.foodwastesavior.webapp.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class RegisterMyStoreRequest {
    private String idToken;
    private AddressInfo addressInfo;

    @Data
    @AllArgsConstructor
    public static class AddressInfo {
        private String addressDetail;
        private String country;
        private String city;
        private Integer postalCode;
        private Double latitude;
        private Double longitude;
    }
}
