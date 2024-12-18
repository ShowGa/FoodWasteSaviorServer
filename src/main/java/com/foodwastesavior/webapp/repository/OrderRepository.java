package com.foodwastesavior.webapp.repository;

import com.foodwastesavior.webapp.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    //

    @Query("SELECT COUNT(o) FROM Order o " +
            "WHERE o.pack.packageId = :packageId AND o.orderDate = :today")
    Integer countOrdersByPackageIdAndOrderDate(
            @Param("packageId") Integer packageId,
            @Param("today") LocalDate today
    );

}

