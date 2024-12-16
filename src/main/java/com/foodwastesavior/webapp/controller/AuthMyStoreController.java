package com.foodwastesavior.webapp.controller;

import com.foodwastesavior.webapp.request.IdTokenRequest;
import com.foodwastesavior.webapp.request.RegisterMyStoreRequest;
import com.foodwastesavior.webapp.response.ApiResponse;
import com.foodwastesavior.webapp.response.LoginResponse;
import com.foodwastesavior.webapp.response.RegisterMyStoreResponse;
import com.foodwastesavior.webapp.service.AuthMyStoreService;
import com.foodwastesavior.webapp.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api-mystore/auth")  // 設定路徑為 api/auth
public class AuthMyStoreController {

    AuthService authService;
    AuthMyStoreService authMyStoreService;

    @Autowired
    public AuthMyStoreController(AuthService authService, AuthMyStoreService authMyStoreService) {
        this.authService = authService;
        this.authMyStoreService = authMyStoreService;
    }

    // MyStore Google OAuth register
    //
    @PostMapping("/googleoauth-register")
    public ResponseEntity<ApiResponse<RegisterMyStoreResponse>> googleOAuthMyStore(@RequestBody RegisterMyStoreRequest registerMyStoreRequest) {

         RegisterMyStoreResponse rR = authMyStoreService.googleOAuthMyStore(registerMyStoreRequest);

        return ResponseEntity.ok(ApiResponse.success(201, "Create Store Successfully !", rR));

    }

    @GetMapping("/googleoauth-login")
    public ResponseEntity<ApiResponse<RegisterMyStoreResponse>> googleOAuthMyStoreLogin(@RequestHeader("Authorization") String authorizationHeader) {
        String idToken = authorizationHeader.substring(7);

        RegisterMyStoreResponse rR = authMyStoreService.googleOAuthMyStoreLogin(idToken);

        return ResponseEntity.ok(ApiResponse.success(200, "Create Store Successfully !", rR));
    }
}
