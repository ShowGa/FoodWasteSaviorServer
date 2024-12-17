package com.foodwastesavior.webapp.repository;

import com.foodwastesavior.webapp.model.entity.Store;
import com.foodwastesavior.webapp.response.storeResponse.SearchCardRes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store, Integer> {
    Optional<Store> findByEmail(String email);

    // find store with distance
    @Query(value = """
    SELECT s.store_id, 
           s.cover_image_url, 
           s.name, 
           a.address_detail, 
           a.longitude, 
           a.latitude, 
           s.rating, 
           ST_Distance_Sphere(POINT(a.longitude, a.latitude), POINT(:userLongitude, :userLatitude)) AS distance
    FROM store s
    JOIN address a ON s.store_id = a.store_id
    WHERE ST_Distance_Sphere(POINT(a.longitude, a.latitude), POINT(:userLongitude, :userLatitude)) <= :distanceInMeters
    """, nativeQuery = true)
    List<Object[]> findStoresWithinDistance(
            @Param("userLongitude") Double userLongitude,
            @Param("userLatitude") Double userLatitude,
            @Param("distanceInMeters") Double distanceInMeters
    );


}


