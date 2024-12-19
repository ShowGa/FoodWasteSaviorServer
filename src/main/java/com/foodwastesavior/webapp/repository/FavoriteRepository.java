package com.foodwastesavior.webapp.repository;

import com.foodwastesavior.webapp.model.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
    //
    @Query("""
        SELECT COUNT(f) > 0
        FROM Favorite f
        WHERE f.user.userId = :userId AND f.pack.packageId = :packageId
    """)
    Boolean existsByUserIdAndPackageId(@Param("userId") Integer userId, @Param("packageId") Integer packageId);

}

