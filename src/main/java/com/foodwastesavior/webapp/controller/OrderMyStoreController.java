package com.foodwastesavior.webapp.controller;

import com.foodwastesavior.webapp.response.ApiResponse;
import com.foodwastesavior.webapp.response.orderRes.MyStorePendingOrdersRes;

import com.foodwastesavior.webapp.service.OrderService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api-mystore/order")
public class OrderMyStoreController {

    private final OrderService orderService;

    public OrderMyStoreController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/getwaitforconfirmorderlist")
    public ResponseEntity<ApiResponse<List<MyStorePendingOrdersRes>>> getAllWaitingForConfirmOrdersList(@RequestHeader("Authorization") String authorizationHeader) {
        String jwt = authorizationHeader.substring(7);

        List<MyStorePendingOrdersRes> results = orderService.getAllWaitingForConfirmOrdersList(jwt);

        return ResponseEntity.ok(ApiResponse.success(200, "Found waiting for confirm list", results));
    }
}
