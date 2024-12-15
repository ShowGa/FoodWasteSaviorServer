package com.foodwastesavior.webapp.service;

import com.foodwastesavior.webapp.model.entity.Package;
import com.foodwastesavior.webapp.response.packagesResponse.MyStoreDashboardPackageCardResponse;
import com.foodwastesavior.webapp.response.packagesResponse.MyStorePackageDetailRes;

import java.util.List;

public interface PackageService {
    MyStoreDashboardPackageCardResponse createNewPackage (String jwt);

    List<MyStoreDashboardPackageCardResponse> getAllMyStorePackageList(String jwt);

    MyStorePackageDetailRes getMyStorePackageOverview (String jwt, Integer packageId);
}

