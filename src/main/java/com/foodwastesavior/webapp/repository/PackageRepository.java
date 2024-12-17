package com.foodwastesavior.webapp.repository;


import com.foodwastesavior.webapp.model.entity.Package;
import com.foodwastesavior.webapp.response.MyStorePackagesResponse.MyStoreDashboardPackageCardResponse;
import com.foodwastesavior.webapp.response.packageRes.StoreDetailPackageCardRes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PackageRepository extends JpaRepository<Package, Integer> {
    //
    @Query(value = """
        SELECT new com.foodwastesavior.webapp.response.MyStorePackagesResponse.MyStoreDashboardPackageCardResponse(p.packageId, p.name, p.coverImageUrl, p.category)
        FROM Package p 
        WHERE p.store.storeId = :storeId 
        ORDER BY p.createdAt DESC
        """)
    List<MyStoreDashboardPackageCardResponse> getAllMyStorePackageList(@Param("storeId")Integer storeId);

    // ========== user ========== //
    @Query(value = """
        SELECT new com.foodwastesavior.webapp.response.packageRes.StoreDetailPackageCardRes(p.packageId, p.coverImageUrl, p.name, p.discountPrice)
        FROM Package p
        WHERE p.store.storeId = :storeId
        """)
    List<StoreDetailPackageCardRes> findPackagesByStoreId(@Param("storeId") Integer storeId);
}

