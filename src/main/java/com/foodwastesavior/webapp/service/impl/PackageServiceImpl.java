package com.foodwastesavior.webapp.service.impl;

import com.foodwastesavior.webapp.model.entity.Package;
import com.foodwastesavior.webapp.model.entity.PackageSalesRule;
import com.foodwastesavior.webapp.model.entity.Store;
import com.foodwastesavior.webapp.repository.PackageRepository;
import com.foodwastesavior.webapp.repository.StoreRepository;
import com.foodwastesavior.webapp.response.packagesResponse.MyStoreDashboardPackageCardResponse;
import com.foodwastesavior.webapp.service.PackageSalesRulesService;
import com.foodwastesavior.webapp.service.PackageService;
import com.foodwastesavior.webapp.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.persistence.EnumType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


        return new MyStoreDashboardPackageCardResponse(savedPack.getPackageId(), savedPack.getName());
    }
}
