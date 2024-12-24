package com.foodwastesavior.webapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StoreProfileDTO {
    private String name;
    private String coverImageUrl;
    private String logoImageUrl;
    private String about;
}
