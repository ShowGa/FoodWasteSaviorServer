package com.foodwastesavior.webapp.controller;

import com.foodwastesavior.webapp.response.ApiResponse;
import com.foodwastesavior.webapp.response.orderRes.UserContributionRes;
import com.foodwastesavior.webapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/order")
public class OrderController {

    OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/user-contribution/{userId}")
    public ResponseEntity<ApiResponse<UserContributionRes>> userContributionWithOrder(@PathVariable Integer userId, @RequestHeader("Authorization") String authorizationHeader) {

        String jwt = authorizationHeader.substring(7);

        // orderservice
        UserContributionRes result = orderService.countUserContribution(userId, jwt);

        return ResponseEntity.ok(ApiResponse.success(200, "Count user contribution successfully !", result));
    }

}
