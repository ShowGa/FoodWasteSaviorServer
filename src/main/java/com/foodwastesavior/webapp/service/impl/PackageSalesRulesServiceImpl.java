package com.foodwastesavior.webapp.service.impl;

import com.foodwastesavior.webapp.model.entity.Package;
import com.foodwastesavior.webapp.model.entity.PackageSalesRule;
import com.foodwastesavior.webapp.service.PackageSalesRulesService;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PackageSalesRulesServiceImpl implements PackageSalesRulesService {

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
}
