package com.foodwastesavior.webapp.repository;

import com.foodwastesavior.webapp.model.entity.PackageSalesRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PackageSalesRuleRepository extends JpaRepository<PackageSalesRule, Integer> {
    //
    List<PackageSalesRule> findByPackPackageId(Integer packageId);

    // =================== user ===================== //
    @Query ("""
            SELECT psr FROM PackageSalesRule psr
            WHERE psr.pack.packageId = :packageId AND 
            psr.weekday = :weekday
            """)
    Optional<PackageSalesRule> findByPackageIdAndWeekday(
            @Param("packageId") Integer packageId,
            @Param("weekday") Integer weekday
    );
}

