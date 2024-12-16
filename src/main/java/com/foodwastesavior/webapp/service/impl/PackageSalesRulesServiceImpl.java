package com.foodwastesavior.webapp.service.impl;

import com.foodwastesavior.webapp.exception.NotFoundException;
import com.foodwastesavior.webapp.model.entity.Package;
import com.foodwastesavior.webapp.model.entity.PackageSalesRule;
import com.foodwastesavior.webapp.repository.PackageSalesRuleRepository;
import com.foodwastesavior.webapp.request.MyStorePackageSalesRuleReq;
import com.foodwastesavior.webapp.response.packagesResponse.PackageDetailRes;
import com.foodwastesavior.webapp.response.packagesResponse.PackageSalesRulesRes;
import com.foodwastesavior.webapp.service.PackageSalesRulesService;
import com.foodwastesavior.webapp.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PackageSalesRulesServiceImpl implements PackageSalesRulesService {

    private PackageSalesRuleRepository packageSalesRuleRepository;

    @Autowired
    public PackageSalesRulesServiceImpl(PackageSalesRuleRepository psrR) {
        this.packageSalesRuleRepository = psrR;
    }

    @Override
    public List<PackageSalesRule> createAllPackageSalesRule(Package newPackage) {
        List<PackageSalesRule> salesRules = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            PackageSalesRule rule = new PackageSalesRule();
            rule.setWeekday(i);
            rule.setQuantity(10); // Default quantity
            rule.setPickupStartTime(LocalTime.of(18, 0)); // Default start time
            rule.setPickupEndTime(LocalTime.of(19, 0)); // Default end time
            rule.setPack(newPackage); // Set the relationship
            salesRules.add(rule);
        }

        return salesRules;
    }

    @Override
    public List<PackageSalesRulesRes> getMyStorePackageSchedules(String jwt, Integer packageId) {
        // verify token first
        String subjectInfo = JwtUtil.validateToken(jwt);

        // find package
        List<PackageSalesRulesRes> salesRulesRes = packageSalesRuleRepository.findByPackPackageId(packageId).stream().map(rule -> new PackageSalesRulesRes(
                        rule.getRulesId(),
                        rule.getWeekday(),
                        rule.getQuantity(),
                        rule.getPickupStartTime(),
                        rule.getPickupEndTime(),
                        rule.getIsActive()
                ))
                .toList();

        return salesRulesRes;}

    @Override
    public PackageSalesRulesRes updateMyStorePackageSchedule(String jwt, Integer rulesId, MyStorePackageSalesRuleReq mspsrR) {
        // Verify token
        String subjectInfo = JwtUtil.validateToken(jwt);

        // Find the PackageSalesRule by rulesId
        PackageSalesRule foundRule = packageSalesRuleRepository.findById(rulesId)
                .orElseThrow(() -> new NotFoundException("糟糕! 找不到這個銷售規則，無法更新!"));

        // Update the fields of the found PackageSalesRule
        foundRule.setWeekday(mspsrR.getWeekday());
        foundRule.setQuantity(mspsrR.getQuantity());
        foundRule.setPickupStartTime(mspsrR.getPickupStartTime());
        foundRule.setPickupEndTime(mspsrR.getPickupEndTime());
        foundRule.setIsActive(mspsrR.getIsActive());

        // Save the updated PackageSalesRule
        PackageSalesRule updatedRule = packageSalesRuleRepository.save(foundRule);

        // Convert the updated entity into a response DTO
        return new PackageSalesRulesRes(
                updatedRule.getRulesId(),
                updatedRule.getWeekday(),
                updatedRule.getQuantity(),
                updatedRule.getPickupStartTime(),
                updatedRule.getPickupEndTime(),
                updatedRule.getIsActive()
        );
    }

}
