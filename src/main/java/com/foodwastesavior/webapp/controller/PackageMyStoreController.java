package com.foodwastesavior.webapp.controller;

import com.foodwastesavior.webapp.request.MyStorePackageDetailReq;
import com.foodwastesavior.webapp.request.MyStorePackageSalesRuleReq;
import com.foodwastesavior.webapp.response.ApiResponse;
import com.foodwastesavior.webapp.response.MyStorePackagesResponse.MyStoreDashboardPackageCardResponse;
import com.foodwastesavior.webapp.response.MyStorePackagesResponse.PackageDetailRes;
import com.foodwastesavior.webapp.response.MyStorePackagesResponse.PackageSalesRulesRes;
import com.foodwastesavior.webapp.service.PackageSalesRulesService;
import com.foodwastesavior.webapp.service.PackageService;
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
    public ResponseEntity<ApiResponse<PackageDetailRes>> getMyStorePackageOverview (@RequestHeader("Authorization") String authorizationHeader, @PathVariable Integer packageId) {
        String jwt = authorizationHeader.substring(7);

        PackageDetailRes mspdr = packageService.getMyStorePackageOverview(jwt, packageId);

        return ResponseEntity.ok(ApiResponse.success(200, "Found Package Sucessfully !", mspdr));
    }

    @GetMapping("/packageschedule/{packageId}")
    public ResponseEntity<ApiResponse<List<PackageSalesRulesRes>>> getMyStorePackageSchedules (@RequestHeader("Authorization") String authorizationHeader, @PathVariable Integer packageId) {
        String jwt = authorizationHeader.substring(7);

        List<PackageSalesRulesRes> packageSalesRules = packageSalesRulesService.getMyStorePackageSchedules(jwt, packageId);

        return ResponseEntity.ok(ApiResponse.success(200, "Found Package Sucessfully !", packageSalesRules));
    }

    @PostMapping("/updatepackageoverview/{packageId}")
    public ResponseEntity<ApiResponse<PackageDetailRes>> updateMyStorePackageOverview (@RequestHeader("Authorization") String authorizationHeader, @PathVariable Integer packageId , @RequestBody MyStorePackageDetailReq mspdReq) {
        String jwt = authorizationHeader.substring(7);

        PackageDetailRes mspdr = packageService.updateMyStorePackageOverview(jwt, packageId, mspdReq);

        return ResponseEntity.ok(ApiResponse.success(200, "Found Package Sucessfully !", mspdr));
    }

    @PostMapping("/updatepackageschedule/{rulesId}")
    public ResponseEntity<ApiResponse<PackageSalesRulesRes>> updateMyStorePackageSchedule (@RequestHeader("Authorization") String authorizationHeader, @PathVariable Integer rulesId , @RequestBody MyStorePackageSalesRuleReq mspsrReq) {
        String jwt = authorizationHeader.substring(7);

        PackageSalesRulesRes psrR = packageSalesRulesService.updateMyStorePackageSchedule(jwt, rulesId, mspsrReq);

        return ResponseEntity.ok(ApiResponse.success(200, "Found Package Sucessfully !", psrR));
    }
}
