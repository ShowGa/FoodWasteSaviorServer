package com.foodwastesavior.webapp.service;


import com.foodwastesavior.webapp.model.entity.Package;
import com.foodwastesavior.webapp.model.entity.PackageSalesRule;

import java.util.List;

public interface PackageSalesRulesService {
    List<PackageSalesRule> createAllPackageSalesRule(Package newPackage);
}
