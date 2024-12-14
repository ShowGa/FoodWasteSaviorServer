package com.foodwastesavior.webapp.repository;


import com.foodwastesavior.webapp.model.entity.Package;
import com.foodwastesavior.webapp.response.packagesResponse.MyStoreDashboardPackageCardResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PackageRepository extends JpaRepository<Package, Integer> {
    //
    @Query(value = """
        SELECT new com.foodwastesavior.webapp.response.packagesResponse.MyStoreDashboardPackageCardResponse(p.packageId, p.name)
        FROM Package p 
        WHERE p.store.storeId = :storeId 
        ORDER BY p.createdAt DESC
        """)
    List<MyStoreDashboardPackageCardResponse> getAllMyStorePackageList(@Param("storeId")Integer storeId);
}

