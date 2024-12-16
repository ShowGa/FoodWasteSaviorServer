package com.foodwastesavior.webapp.response;

import com.foodwastesavior.webapp.model.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressRes {
    private String addressDetail;
    private String country;
    private String city;
    private Integer postalCode;
    private Double latitude;
    private Double longitude;
}
