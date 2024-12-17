package com.foodwastesavior.webapp.service;

import com.foodwastesavior.webapp.request.MyStorePackageDetailReq;
import com.foodwastesavior.webapp.response.MyStorePackagesResponse.MyStoreDashboardPackageCardResponse;
import com.foodwastesavior.webapp.response.MyStorePackagesResponse.PackageDetailRes;
import com.foodwastesavior.webapp.response.packageRes.StoreDetailPackageCardRes;

import java.util.List;

public interface PackageService {
    // ============== mystore =============== //
    MyStoreDashboardPackageCardResponse createNewPackage (String jwt);

    List<MyStoreDashboardPackageCardResponse> getAllMyStorePackageList(String jwt);

    PackageDetailRes getMyStorePackageOverview (String jwt, Integer packageId);

    PackageDetailRes updateMyStorePackageOverview (String jwt, Integer packageId, MyStorePackageDetailReq mspdReq);

    // ============== user =============== //
    List<StoreDetailPackageCardRes> findStoreDetailPackageCard(String jwt, Integer storeId);
}

