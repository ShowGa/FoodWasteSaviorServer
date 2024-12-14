package com.foodwastesavior.webapp.controller;

import com.foodwastesavior.webapp.response.ApiResponse;
import com.foodwastesavior.webapp.response.packagesResponse.MyStoreDashboardPackageCardResponse;
import com.foodwastesavior.webapp.response.packagesResponse.MyStoreDashboardPackageCardResponse;
import com.foodwastesavior.webapp.service.PackageService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api-mystore/packages")
public class PackageMyStoreController {

    PackageService packageService;

    @Autowired
    public PackageMyStoreController(PackageService packageService) {
        this.packageService = packageService;
    }

    // mystore create package route
    @GetMapping("/create")
    public ResponseEntity<ApiResponse<MyStoreDashboardPackageCardResponse>> createNewPackage(@RequestHeader("Authorization") String authorizationHeader) {
        String jwt = authorizationHeader.substring(7);

        System.out.println(authorizationHeader);
        System.out.println(jwt);

        MyStoreDashboardPackageCardResponse msdpcr = packageService.createNewPackage(jwt);

        return ResponseEntity.ok(ApiResponse.success(201, "Create Package Successfully", msdpcr));
    }
}
