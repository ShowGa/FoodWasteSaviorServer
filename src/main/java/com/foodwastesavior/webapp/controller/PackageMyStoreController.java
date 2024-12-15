package com.foodwastesavior.webapp.controller;

import com.foodwastesavior.webapp.response.ApiResponse;
import com.foodwastesavior.webapp.response.packagesResponse.MyStoreDashboardPackageCardResponse;
import com.foodwastesavior.webapp.response.packagesResponse.MyStorePackageDetailRes;
import com.foodwastesavior.webapp.response.packagesResponse.PackageSalesRulesRes;
import com.foodwastesavior.webapp.service.PackageSalesRulesService;
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
    PackageSalesRulesService packageSalesRulesService;

    @Autowired
    public PackageMyStoreController(PackageService packageService, PackageSalesRulesService packageSalesRulesService) {
        this.packageService = packageService;
        this.packageSalesRulesService = packageSalesRulesService;
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

    @GetMapping("/packageoverview/{packageId}")
    public ResponseEntity<ApiResponse<MyStorePackageDetailRes>> getMyStorePackageOverview (@RequestHeader("Authorization") String authorizationHeader, @PathVariable Integer packageId) {
        String jwt = authorizationHeader.substring(7);

        MyStorePackageDetailRes mspdr = packageService.getMyStorePackageOverview(jwt, packageId);

        return ResponseEntity.ok(ApiResponse.success(200, "Found Package Sucessfully !", mspdr));
    }

    @GetMapping("/packageschedule/{packageId}")
    public ResponseEntity<ApiResponse<List<PackageSalesRulesRes>>> getMyStorePackageSchedules (@RequestHeader("Authorization") String authorizationHeader, @PathVariable Integer packageId) {
        String jwt = authorizationHeader.substring(7);

        List<PackageSalesRulesRes> packageSalesRules = packageSalesRulesService.getMyStorePackageSchedules(jwt, packageId);

        return ResponseEntity.ok(ApiResponse.success(200, "Found Package Sucessfully !", packageSalesRules));
    }
}
