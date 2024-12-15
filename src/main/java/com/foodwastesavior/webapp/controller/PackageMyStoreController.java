package com.foodwastesavior.webapp.controller;

import com.foodwastesavior.webapp.response.ApiResponse;
import com.foodwastesavior.webapp.response.packagesResponse.MyStoreDashboardPackageCardResponse;
import com.foodwastesavior.webapp.service.PackageService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

        MyStoreDashboardPackageCardResponse msdpcr = packageService.createNewPackage(jwt);

        return ResponseEntity.ok(ApiResponse.success(201, "Create Package Successfully", msdpcr));
    }

    @GetMapping("/getallpackagelist")
    public ResponseEntity<ApiResponse<List<MyStoreDashboardPackageCardResponse>>> getAllMyStorePackageList(@RequestHeader("Authorization") String authorizationHeader) {
        String jwt = authorizationHeader.substring(7);

        List<MyStoreDashboardPackageCardResponse> msdpcrS = packageService.getAllMyStorePackageList(jwt);

        if (msdpcrS.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error(404, "您還沒有建立商品喔!"));
        }

        return ResponseEntity.ok(ApiResponse.success(200, "Found Package Successfully !", msdpcrS));
    }
}
