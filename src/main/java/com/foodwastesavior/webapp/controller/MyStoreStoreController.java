package com.foodwastesavior.webapp.controller;

import com.foodwastesavior.webapp.model.dto.StoreProfileDTO;
import com.foodwastesavior.webapp.model.entity.Store;
import com.foodwastesavior.webapp.response.ApiResponse;
import com.foodwastesavior.webapp.service.StoreService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api-mystore/store")
public class MyStoreStoreController {

    StoreService storeService;

    @Autowired
    public MyStoreStoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping("/get-store-info")
    public ResponseEntity<ApiResponse<StoreProfileDTO>> getStoreInfo(@RequestHeader("Authorization") String authorizationHeader) {
        String jwt = authorizationHeader.substring(7);

        StoreProfileDTO result = storeService.getStoreInfo(jwt);

        return ResponseEntity.ok(ApiResponse.success(200, "Found store information successfully!", result));
    }

    @PostMapping("/update-store-info")
    public ResponseEntity<ApiResponse<StoreProfileDTO>> updateStoreInfo(@RequestBody StoreProfileDTO updatedInfo, @RequestHeader("Authorization") String authorizationHeader) {
        String jwt = authorizationHeader.substring(7);

        StoreProfileDTO result = storeService.updateStoreInfo(updatedInfo, jwt);

        return ResponseEntity.ok(ApiResponse.success(200, "Update store information successfully!", result));
    }

}
