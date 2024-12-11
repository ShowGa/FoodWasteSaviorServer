package com.foodwastesavior.webapp.service;

import com.foodwastesavior.webapp.response.LoginResponse;

public interface AuthService {

    LoginResponse googleOAuth (String idToken);
}
