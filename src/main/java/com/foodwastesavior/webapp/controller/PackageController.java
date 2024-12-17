package com.foodwastesavior.webapp.controller;

import com.foodwastesavior.webapp.response.ApiResponse;
import com.foodwastesavior.webapp.response.packageRes.StoreDetailPackageCardRes;
import com.foodwastesavior.webapp.response.storeResponse.SearchCardRes;
import com.foodwastesavior.webapp.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/package")
public class PackageController {

    PackageService packageService;

    @Autowired
    public PackageController(PackageService packageService) {
        this.packageService = packageService;
    }

    @GetMapping("/storedetail-packagecard/{storeId}")
    public ResponseEntity<ApiResponse<List<StoreDetailPackageCardRes>>> findStoreWithDistance(
            @PathVariable Integer storeId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        String jwt = authorizationHeader.substring(7);

        List<StoreDetailPackageCardRes> results = packageService.findStoreDetailPackageCard(jwt, storeId);

        return ResponseEntity.ok(ApiResponse.success(200, "Find StoreDetail PackageCard successfully !", results));
    }
}
