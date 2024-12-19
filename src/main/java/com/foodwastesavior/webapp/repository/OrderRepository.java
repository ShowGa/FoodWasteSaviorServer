package com.foodwastesavior.webapp.repository;

import com.foodwastesavior.webapp.model.entity.Order;
import com.foodwastesavior.webapp.response.orderRes.UserContributionRes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    //

    @Query("SELECT COUNT(o) FROM Order o " +
            "WHERE o.pack.packageId = :packageId AND o.orderDate = :today")
    Integer countOrdersByPackageIdAndOrderDate(
            @Param("packageId") Integer packageId,
            @Param("today") LocalDate today
    );

    @Query("""
            SELECT COALESCE(COUNT(o), 0L)
            FROM Order o
            WHERE o.user.userId = :userId
            """)
    Optional<Long> countUserOrderAmount(@Param("userId") Integer userId);

    @Query("""
            SELECT COALESCE(SUM(o.pack.originPrice - o.pack.discountPrice), 0L)
            FROM Order o
            WHERE o.user.userId = :userId
            """)
    Optional<Long> countUserSavedMoney(@Param("userId") Integer userId);

}

