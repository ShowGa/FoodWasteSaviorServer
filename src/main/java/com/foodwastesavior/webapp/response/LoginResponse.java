package com.foodwastesavior.webapp.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private String jwt;

    private Integer userId;
    private String username;
    private String email;
    private String avatarUrl;

    private UserPositionRes userPosition;
}

