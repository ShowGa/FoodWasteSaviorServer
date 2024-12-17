package com.foodwastesavior.webapp.service.impl;

import com.foodwastesavior.webapp.exception.NotFoundException;
import com.foodwastesavior.webapp.model.entity.Package;
import com.foodwastesavior.webapp.model.entity.PackageSalesRule;
import com.foodwastesavior.webapp.model.entity.Store;
import com.foodwastesavior.webapp.repository.PackageRepository;
import com.foodwastesavior.webapp.repository.StoreRepository;
import com.foodwastesavior.webapp.request.MyStorePackageDetailReq;
import com.foodwastesavior.webapp.response.MyStorePackagesResponse.MyStoreDashboardPackageCardResponse;
import com.foodwastesavior.webapp.response.MyStorePackagesResponse.PackageDetailRes;
import com.foodwastesavior.webapp.response.packageRes.StoreDetailPackageCardRes;
import com.foodwastesavior.webapp.service.PackageSalesRulesService;
import com.foodwastesavior.webapp.service.PackageService;
import com.foodwastesavior.webapp.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class PackageServiceImpl implements PackageService {

    private final PackageRepository packageRepository;
    private final StoreRepository storeRepository;
    private final PackageSalesRulesService psr;

    // constructor injection
    @Autowired
    public PackageServiceImpl(PackageRepository packageRepository, StoreRepository storeRepository, PackageSalesRulesService psr) {
        this.packageRepository = packageRepository;
        this.storeRepository = storeRepository;
        this.psr = psr;
    }

    /* ========================== store ========================== */
    @Override
    public MyStoreDashboardPackageCardResponse createNewPackage(String jwt) {
        // verify token first
        String subjectInfo = JwtUtil.validateToken(jwt);

        // get store info
        Store foundStore = storeRepository.findByEmail(subjectInfo).get();

        // create newPackage
        int defaultDiscountPrice = 50;

        Package newPackage = new Package();
        newPackage.setName("驚喜包名字");
        newPackage.setCoverImageUrl("");
        newPackage.setDescription("請介紹你的驚喜包");
        newPackage.setAllergensDesc("請說明食物是否含有潛在過敏原");
        newPackage.setCategory(Package.Category.MEALS);
        newPackage.setOriginPrice(defaultDiscountPrice * 2);
        newPackage.setDiscountPrice(defaultDiscountPrice);

        newPackage.setStore(foundStore);

        // create 7 days' rule
        List<PackageSalesRule> newPsr = psr.createAllPackageSalesRule(newPackage);

        // set relation
        newPackage.setPackageSalesRules(newPsr);

        // save
        Package savedPack = packageRepository.save(newPackage);


        return new MyStoreDashboardPackageCardResponse(savedPack.getPackageId(), savedPack.getName(), savedPack.getCoverImageUrl(), savedPack.getCategory());
    }

    @Override
    public List<MyStoreDashboardPackageCardResponse> getAllMyStorePackageList(String jwt) {
        // verify token first
        String subjectInfo = JwtUtil.validateToken(jwt);

        // get store info
        Store foundStore = storeRepository.findByEmail(subjectInfo).get();

        // get all packages
        List<MyStoreDashboardPackageCardResponse> foundedPackages = packageRepository.getAllMyStorePackageList(foundStore.getStoreId());

        if (foundedPackages.isEmpty()) {
            return Collections.emptyList();
        }

        return foundedPackages;
    }

    @Override
    public PackageDetailRes getMyStorePackageOverview(String jwt, Integer packageId) {

        // verify token first
        String subjectInfo = JwtUtil.validateToken(jwt);

        // find package
        Package foundPackage = packageRepository.findById(packageId).orElseThrow(() -> new NotFoundException("糟糕!找不到您要得驚喜包 !"));

        // Convert Package to MyStorePackageDetailRes
        return new PackageDetailRes(
                foundPackage.getPackageId(),
                foundPackage.getName(),
                foundPackage.getCoverImageUrl(),
                foundPackage.getDescription(),
                foundPackage.getAllergensDesc(),
                foundPackage.getCategory(),
                foundPackage.getOriginPrice(),
                foundPackage.getDiscountPrice(),
                foundPackage.getIsActive()
        );
    }

    @Override
    public PackageDetailRes updateMyStorePackageOverview(String jwt, Integer packageId, MyStorePackageDetailReq mspdReq) {
        // Verify token and extract subject information
        String subjectInfo = JwtUtil.validateToken(jwt);

        // Find the package by ID
        Package foundPackage = packageRepository.findById(packageId)
                .orElseThrow(() -> new NotFoundException("糟糕!找不到您的驚喜包，無法更新!"));

        // Update package data with the new values from the request
        foundPackage.setName(mspdReq.getPackageName());
        foundPackage.setCoverImageUrl(mspdReq.getPackageCoverImageUrl());
        foundPackage.setDescription(mspdReq.getPackageDescription());
        foundPackage.setAllergensDesc(mspdReq.getPackageAllergensDesc());
        foundPackage.setCategory(mspdReq.getPackageCategory());
        foundPackage.setOriginPrice(mspdReq.getOriginPrice());
        foundPackage.setDiscountPrice(mspdReq.getDiscountPrice());
        foundPackage.setIsActive(mspdReq.getIsActive());

        // Save the updated entity
        Package updatedPackage = packageRepository.save(foundPackage);

        // Convert the updated entity into a response DTO
        return new PackageDetailRes(
                updatedPackage.getPackageId(),
                updatedPackage.getName(),
                updatedPackage.getCoverImageUrl(),
                updatedPackage.getDescription(),
                updatedPackage.getAllergensDesc(),
                updatedPackage.getCategory(),
                updatedPackage.getOriginPrice(),
                updatedPackage.getDiscountPrice(),
                updatedPackage.getIsActive()
        );
    }

    @Override
    public List<StoreDetailPackageCardRes> findStoreDetailPackageCard(String jwt, Integer storeId) {
        // Verify token and extract subject information
        String subjectInfo = JwtUtil.validateToken(jwt);

        // find package and sales rules
        return packageRepository.findPackagesByStoreId(storeId);
    }


}
