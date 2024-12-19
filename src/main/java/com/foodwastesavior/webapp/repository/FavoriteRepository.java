package com.foodwastesavior.webapp.repository;

import com.foodwastesavior.webapp.model.entity.Favorite;
import com.foodwastesavior.webapp.response.packageRes.FavoriteCardRes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
    //
    @Query("""
        SELECT COUNT(f) > 0
        FROM Favorite f
        WHERE f.user.userId = :userId AND f.pack.packageId = :packageId
    """)
    Boolean existsByUserIdAndPackageId(@Param("userId") Integer userId, @Param("packageId") Integer packageId);

    @Modifying
    @Query("""
    DELETE FROM Favorite f
    WHERE f.user.userId = :userId AND f.pack.packageId = :packageId
""")
    void deleteByUserIdAndPackageId(@Param("userId") Integer userId, @Param("packageId") Integer packageId);

    @Query("""
    SELECT new com.foodwastesavior.webapp.response.packageRes.FavoriteCardRes(
        p.packageId,
        p.name,
        p.coverImageUrl,
        p.discountPrice,
        s.name,
        s.logoImageUrl,
        psr.pickupStartTime,
        psr.pickupEndTime,
        psr.isActive
    )
    FROM Favorite f
    JOIN f.pack p
    JOIN p.store s
    JOIN PackageSalesRule psr ON psr.pack.packageId = p.packageId
    WHERE f.user.userId = :userId 
      AND psr.weekday = :todayWeekday
""")
    List<FavoriteCardRes> findUserFavoritesWithDetails(
            @Param("userId") Integer userId,
            @Param("todayWeekday") Integer todayWeekday
    );

}

