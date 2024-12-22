package com.foodwastesavior.webapp.repository;

import com.foodwastesavior.webapp.model.entity.Order;
import com.foodwastesavior.webapp.response.orderRes.MyStoreOrdersListRes;
import com.foodwastesavior.webapp.response.orderRes.UserOrderList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    //

    @Query("SELECT COALESCE(SUM(o.quantity), 0L) FROM Order o " +
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

    @Query("""
    SELECT new com.foodwastesavior.webapp.response.orderRes.UserOrderList(
        o.orderId,
        o.orderStatus,
        psr.pickupStartTime,
        psr.pickupEndTime,
        s.logoImageUrl,
        s.name
    )
    FROM Order o
    JOIN o.pack p
    JOIN p.packageSalesRules psr
    JOIN p.store s
    WHERE o.user.userId = :userId
      AND o.orderDate = :today
      AND psr.weekday = :todayWeekday
      AND o.orderStatus IN ('WAITFORCONFIRM', 'PENDING', 'READY')
""")
    List<UserOrderList> findOrdersByUserIdAndWeekday(
            @Param("userId") Integer userId,
            @Param("today") LocalDate today,
            @Param("todayWeekday") Integer todayWeekday
    );

    @Query("""
    SELECT o
    FROM Order o
    WHERE o.user.userId = :userId
      AND o.orderId = :orderId
      AND o.orderDate = :orderDate
""")
    Optional<Order> findOrderDetailByUserIdAndOrderIdAndOrderDate(
            @Param("userId") Integer userId,
            @Param("orderId") Integer orderId,
            @Param("orderDate") LocalDate orderDate
    );

    // ============= mystore ============ //

    @Query("""
    SELECT new com.foodwastesavior.webapp.response.orderRes.MyStoreOrdersListRes(
        o.orderId,
        o.confirmationCode,
        o.quantity,
        p.name,
        p.coverImageUrl
    )
    FROM Order o
    JOIN o.pack p
    WHERE o.store.storeId = :storeId
      AND o.orderDate = :today
      AND o.orderStatus = 'WAITFORCONFIRM'
""")
    List<MyStoreOrdersListRes> getAllWaitingForConfirmOrdersList(
            @Param("storeId") Integer storeId,
            @Param("today") LocalDate today
    );

    @Query("""
    SELECT new com.foodwastesavior.webapp.response.orderRes.MyStoreOrdersListRes(
        o.orderId,
        o.confirmationCode,
        o.quantity,
        p.name,
        p.coverImageUrl
    )
    FROM Order o
    JOIN o.pack p
    WHERE o.store.storeId = :storeId
      AND o.orderDate = :today
      AND o.orderStatus IN ('PENDING', 'READY')
""")
    List<MyStoreOrdersListRes> getAllConfirmedOrdersList(
            @Param("storeId") Integer storeId,
            @Param("today") LocalDate today
    );
}

