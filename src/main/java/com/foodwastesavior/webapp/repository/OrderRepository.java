package com.foodwastesavior.webapp.repository;

import com.foodwastesavior.webapp.model.entity.Order;
import com.foodwastesavior.webapp.response.orderRes.*;
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

    @Query("""
    SELECT new com.foodwastesavior.webapp.response.orderRes.UserOrderHistoryListRes(
        o.orderId,
        o.orderDate,
        s.name,
        s.logoImageUrl
    )
    FROM Order o
    JOIN o.store s
    WHERE o.user.userId = :userId
      AND o.orderStatus = 'COMPLETED'
""")
    List<UserOrderHistoryListRes> findCompletedOrdersByUserId(@Param("userId") Integer userId);


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

    @Query("""
    SELECT new com.foodwastesavior.webapp.response.orderRes.UserOrderHistoryDetailRes(
        o.orderId,
        o.orderStatus,
        o.orderDate,
        o.totalPrice,
        o.quantity,
        o.confirmationCode,
        s.storeId,
        s.logoImageUrl,
        s.name,
        a.addressDetail,
        a.latitude,
        a.longitude,
        p.name,
        p.category
    )
    FROM Order o
    JOIN o.store s
    JOIN s.address a
    JOIN o.pack p
    WHERE o.orderId = :orderId
      AND o.user.userId = :userId
      AND o.orderStatus IN ('COMPLETED', 'CANCEL')
""")
    Optional<UserOrderHistoryDetailRes> findUserOrderHistoryDetail(
            @Param("orderId") Integer orderId,
            @Param("userId") Integer userId
    );

}

