package com.foodwastesavior.webapp.controller;

import com.foodwastesavior.webapp.request.CreateOrderReq;
import com.foodwastesavior.webapp.response.ApiResponse;
import com.foodwastesavior.webapp.response.orderRes.UserContributionRes;
import com.foodwastesavior.webapp.response.orderRes.UserOrderDetailRes;
import com.foodwastesavior.webapp.response.orderRes.UserOrderList;
import com.foodwastesavior.webapp.service.OrderService;
import com.google.protobuf.Api;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/order")
public class OrderController {

    OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/user-contribution")
    public ResponseEntity<ApiResponse<UserContributionRes>> userContributionWithOrder(@PathVariable Integer userId, @RequestHeader("Authorization") String authorizationHeader) {

        String jwt = authorizationHeader.substring(7);

        // orderservice
        UserContributionRes result = orderService.countUserContribution(userId, jwt);

        return ResponseEntity.ok(ApiResponse.success(200, "Count user contribution successfully !", result));
    }

    @PostMapping("/createorder")
    public ResponseEntity<ApiResponse<UserOrderDetailRes>> userCreateOrder(@RequestBody CreateOrderReq createOrderReq, @RequestHeader("Authorization") String authorizationHeader) {
        String jwt = authorizationHeader.substring(7);

        UserOrderDetailRes result = orderService.userCreateOrder(createOrderReq, jwt);

        return ResponseEntity.ok(ApiResponse.success(200, "Create Order Successfully !", result));
    }

    @GetMapping("/user-order-list/{userId}")
    public ResponseEntity<ApiResponse<List<UserOrderList>>> getUserOrderList(@PathVariable Integer userId, @RequestHeader("Authorization") String authorizationHeader) {
        String jwt = authorizationHeader.substring(7);

        // get all list
        List<UserOrderList> result = orderService.getUserOrderList(userId, jwt);

        return ResponseEntity.ok(ApiResponse.success(200, "Found User Order list successfully!", result));
    }
}
