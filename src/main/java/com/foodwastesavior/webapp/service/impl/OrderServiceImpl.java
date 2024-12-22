package com.foodwastesavior.webapp.service.impl;

import com.foodwastesavior.webapp.exception.NotFoundException;
import com.foodwastesavior.webapp.exception.OutOfStockException;
import com.foodwastesavior.webapp.exception.TokenValidationException;
import com.foodwastesavior.webapp.model.entity.*;
import com.foodwastesavior.webapp.model.entity.Package;
import com.foodwastesavior.webapp.repository.*;
import com.foodwastesavior.webapp.request.CreateOrderReq;
import com.foodwastesavior.webapp.response.orderRes.*;
import com.foodwastesavior.webapp.service.OrderService;
import com.foodwastesavior.webapp.utils.JwtUtil;
import com.foodwastesavior.webapp.utils.OrderConfirmCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final PackageRepository packageRepository;
    private final PackageSalesRuleRepository psrRepo;
    private final StoreRepository storeRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository, PackageRepository packageRepository, PackageSalesRuleRepository psrRepo, StoreRepository storeRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.packageRepository = packageRepository;
        this.psrRepo = psrRepo;
        this.storeRepository = storeRepository;
    }

    @Override
    public UserContributionRes countUserContribution(Integer userId, String jwt) {
        // verify
        String subjectInfo = JwtUtil.validateToken(jwt);

        // find userId
        User foundUser = userRepository.findByEmail(subjectInfo).orElseThrow(() -> new NotFoundException("無法根據憑證找到您的用戶資訊!"));

        if (foundUser.getUserId() != userId) {
            throw new TokenValidationException("靠!還想偷偷來啊!");
        }

        // count contribution
        Long orderAmounts = orderRepository.countUserOrderAmount(userId).orElseGet(() -> {
            return 0L;
        });

        Long savedMoney = orderRepository.countUserSavedMoney(userId).orElseGet(() -> {
            return 0L;
        });

        return new UserContributionRes(orderAmounts, savedMoney);
    }

    @Override
    public UserOrderDetailRes userCreateOrder(CreateOrderReq createOrderReq, String jwt) {
        // verify token
        String subjectInfo = JwtUtil.validateToken(jwt);

        // found user

        // find user, package, store
        User foundUser = userRepository.findByEmail(subjectInfo).orElseThrow(() -> new NotFoundException("找不到用戶信息，無法下單!"));

        Package foundPack = packageRepository.findById(createOrderReq.getPackageId()).orElseThrow(() -> new NotFoundException("找不到商品信息!"));

        Store gotStore = foundPack.getStore();

        // today
        LocalDate nowdate = LocalDate.now(ZoneId.of("Asia/Taipei"));

        int todayWeekday = nowdate.getDayOfWeek().getValue() % 7;

        // get sales rule today
        PackageSalesRule todayPsr = psrRepo.findByPackageIdAndWeekday(createOrderReq.getPackageId(), todayWeekday).orElseThrow(() -> new NotFoundException("糟糕!找不到下單資訊!請聯絡我們!"));

        // calculate the order amount and write logic
        Integer countExistedOrders = orderRepository.countOrdersByPackageIdAndOrderDate(createOrderReq.getPackageId(), nowdate);

        if (todayPsr.getQuantity() - countExistedOrders < createOrderReq.getQuantity()) {
            throw new OutOfStockException("糟糕!商品不夠了!");
        }

        // create order
        Integer quantity = createOrderReq.getQuantity();

        Order newOrder = new Order();
        newOrder.setQuantity(quantity);
        newOrder.setTotalPrice(quantity * foundPack.getDiscountPrice());
        newOrder.setTotalOriginPrice(quantity * foundPack.getOriginPrice());
        newOrder.setConfirmationCode(OrderConfirmCodeGenerator.generateOrderId());
        newOrder.setOrderDate(nowdate);
        newOrder.setUser(foundUser);
        newOrder.setPack(foundPack);
        newOrder.setStore(gotStore);

        // save order
        Order savedOrder = orderRepository.save(newOrder);

        return new UserOrderDetailRes(
                savedOrder.getOrderId(),
                savedOrder.getOrderStatus(),
                savedOrder.getQuantity(),
                savedOrder.getTotalPrice(),
                savedOrder.getConfirmationCode(),
                savedOrder.getOrderDate(),
                gotStore.getName(),
                gotStore.getLogoImageUrl(),
                gotStore.getAddress().getAddressDetail(),
                todayPsr.getPickupStartTime(),
                todayPsr.getPickupEndTime()
        );
    }

    @Override
    public List<UserOrderList> getUserOrderList(Integer userId, String jwt) {
        // verify token
        String subjectInfo = JwtUtil.validateToken(jwt);

        // get user order with
        User foundUser = userRepository.findByEmail(subjectInfo).orElseThrow(() -> new NotFoundException("糟糕!沒有找到使用者的資料，取得訂單資料失敗!"));

        LocalDate today = LocalDate.now(ZoneId.of("Asia/Taipei"));

        int todayWeekday = today.getDayOfWeek().getValue() % 7;

        List<UserOrderList> userOrders = orderRepository.findOrdersByUserIdAndWeekday(foundUser.getUserId(), today, todayWeekday);

        if (userOrders.isEmpty()) return Collections.emptyList();

        return userOrders;
    }

    @Override
    public UserOrderDetail getUserOrderDetail(Integer orderId, String jwt) {
        // verify token
        String subjectInfo = JwtUtil.validateToken(jwt);

        LocalDate today = LocalDate.now(ZoneId.of("Asia/Taipei"));

        // get user
        User gotUser = userRepository.findByEmail(subjectInfo).orElseThrow(() -> new NotFoundException("找不到使用者資訊!"));

        // get order with condition
        Order foundOrder = orderRepository.findOrderDetailByUserIdAndOrderIdAndOrderDate(gotUser.getUserId(), orderId, today).orElseThrow(() -> new NotFoundException("沒有找到匹配的訂單!"));

        // get store
        Store gotStore = foundOrder.getStore();

        // get address
        Address gotAddress = gotStore.getAddress();

        // get package
        Package gotPack = foundOrder.getPack();

        // get psr
        int todayWeekday = today.getDayOfWeek().getValue() % 7;

        PackageSalesRule psr = psrRepo.findByPackageIdAndWeekday(gotPack.getPackageId(), todayWeekday).orElseThrow(() -> new NotFoundException("找不到訂單資訊!"));

        UserOrderDetail userOrderDetail = new UserOrderDetail(foundOrder.getOrderId(), foundOrder.getOrderStatus(), foundOrder.getOrderDate(), foundOrder.getTotalPrice(), foundOrder.getQuantity(), foundOrder.getConfirmationCode(),gotStore.getStoreId(), gotStore.getLogoImageUrl(), gotStore.getName(), gotAddress.getAddressDetail(), gotAddress.getLatitude(), gotAddress.getLongitude(), gotPack.getName(), gotPack.getCategory(), psr.getPickupStartTime(), psr.getPickupEndTime());

        return userOrderDetail;

    }

    @Override
    public String completeTheOrder(Integer orderId, String jwt) {
        // verify token
        String subjectInfo = JwtUtil.validateToken(jwt);

        // get user
        User foundUser = userRepository.findByEmail(subjectInfo).orElseThrow(() -> new NotFoundException("沒有找到使用者資訊，無法確認完成訂單!"));

        // find order
        Order foundOrder = orderRepository.findById(orderId).orElseThrow(() -> new NotFoundException("沒有找到訂單資訊，無法完成訂單!"));

        // check if the order belong to user
        if (!Objects.equals(foundUser.getUserId(), foundOrder.getUser().getUserId())) {
            throw new NotFoundException("沒有找到匹配訂單的資訊，無法更改!");
        };

        // update the status of order
        foundOrder.setOrderStatus(Order.OrderStatus.COMPLETED);
        Order savedOrder = orderRepository.save(foundOrder);

        return "訂單已完成!請記得領取食物。";
    }

    @Override
    public List<UserOrderHistoryListRes> getOrderHistoryList(String jwt) {
        // verify token
        String subjectInfo = JwtUtil.validateToken(jwt);

        // get user
        User foundUser = userRepository.findByEmail(subjectInfo).orElseThrow(() -> new NotFoundException("沒有找到使用者，無法找到訂單"));

        // find add the list
        List<UserOrderHistoryListRes> results = orderRepository.findCompletedOrdersByUserId(foundUser.getUserId());

        if (results.isEmpty()) {
            return Collections.emptyList();
        }

        return results;
    }

    // ================== mystore ================== //

    @Override
    public List<MyStoreOrdersListRes> getAllWaitingForConfirmOrdersList(String jwt) {
        // verify token
        String subjectInfo = JwtUtil.validateToken(jwt);

        // get store
        Store gotStore = storeRepository.findByEmail(subjectInfo).orElseThrow(() -> new NotFoundException("沒有找到商家資訊，無法取得訂單!"));

        // get all orders
        LocalDate today = LocalDate.now(ZoneId.of("Asia/Taipei"));

        List<MyStoreOrdersListRes> foundOrdersList = orderRepository.getAllWaitingForConfirmOrdersList(gotStore.getStoreId(), today);

        if (foundOrdersList.isEmpty()) return Collections.emptyList();

        return foundOrdersList;
    }

    @Transactional
    @Override
    public String accepttheorder(Integer orderId, String jwt) {
        // verify token
        String subjectInfo = JwtUtil.validateToken(jwt);

        // getstore
        Store gotStore = storeRepository.findByEmail(subjectInfo).orElseThrow(() -> new NotFoundException("沒有找到商家資訊，無法更新訂單"));

        // get order
        Order foundOrder = orderRepository.findById(orderId).orElseThrow(() -> new NotFoundException("沒有找到這個訂單!請聯絡我們。"));

        // check order is belong to store
        if (!Objects.equals(gotStore.getStoreId(), foundOrder.getStore().getStoreId())) {
            throw new NotFoundException("沒有找到匹配訂單的資訊，無法更改!");
        };

        // update the status of order
        foundOrder.setOrderStatus(Order.OrderStatus.READY);
        Order savedOrder = orderRepository.save(foundOrder);

        return "成功接受訂單!";
    }

    @Override
    public List<MyStoreOrdersListRes> getAllConfirmedOrdersList(String jwt) {
        // verify token
        String subjectInfo = JwtUtil.validateToken(jwt);

        // get store
        Store gotStore = storeRepository.findByEmail(subjectInfo).orElseThrow(() -> new NotFoundException("沒有找到商家資訊，無法取得訂單!"));

        // get all orders
        LocalDate today = LocalDate.now(ZoneId.of("Asia/Taipei"));

        List<MyStoreOrdersListRes> foundOrdersList = orderRepository.getAllConfirmedOrdersList(gotStore.getStoreId(), today);

        if (foundOrdersList.isEmpty()) return Collections.emptyList();

        return foundOrdersList;
    }
}
