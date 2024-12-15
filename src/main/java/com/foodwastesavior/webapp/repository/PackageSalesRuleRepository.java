package com.foodwastesavior.webapp.repository;

import com.foodwastesavior.webapp.model.entity.PackageSalesRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PackageSalesRuleRepository extends JpaRepository<PackageSalesRule, Integer> {
    //
    List<PackageSalesRule> findByPackPackageId(Integer packageId);
}

