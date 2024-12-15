package com.foodwastesavior.webapp.service;

import com.foodwastesavior.webapp.request.MyStorePackageDetailReq;
import com.foodwastesavior.webapp.response.packagesResponse.MyStoreDashboardPackageCardResponse;
import com.foodwastesavior.webapp.response.packagesResponse.PackageDetailRes;

import java.util.List;

public interface PackageService {
    MyStoreDashboardPackageCardResponse createNewPackage (String jwt);

    List<MyStoreDashboardPackageCardResponse> getAllMyStorePackageList(String jwt);

    PackageDetailRes getMyStorePackageOverview (String jwt, Integer packageId);

    PackageDetailRes updateMyStorePackageOverview (String jwt, Integer packageId, MyStorePackageDetailReq mspdReq);
}

