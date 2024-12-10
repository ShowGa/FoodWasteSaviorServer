package com.foodwastesavior.webapp.controller;

import com.foodwastesavior.webapp.model.dto.UserDTO;
import com.foodwastesavior.webapp.response.ApiResponse;
import com.foodwastesavior.webapp.response.LoginResponse;
import com.foodwastesavior.webapp.service.AuthService;
import com.google.api.Http;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@RequestMapping("api/auth")  // 設定路徑為 api/auth
public class AuthController {

    AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // modify exception later
    // verify firebase Token and login or create user then login
    @PostMapping("/googleoauth")
    public ResponseEntity<ApiResponse<String>> googleOAuth(@RequestBody String firebaseToken) {
//        try {
//            LoginResponse loginResponse = authService.googleOAuth(firebaseToken);
//
//            return ResponseEntity.ok(ApiResponse.success(200, "Login Successfully !", loginResponse));
//        } catch (Exception e) {
//            System.out.println(e);
//        }


        return ResponseEntity.ok(ApiResponse.success(200, "Logined Successfully !", "bich"));
    }
}
