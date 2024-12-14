package com.foodwastesavior.webapp.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtTokenInfo {
    private Integer id;
    private String name;
    private String email;
}
