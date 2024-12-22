package com.foodwastesavior.webapp.controller;

import com.foodwastesavior.webapp.model.entity.Review;
import com.foodwastesavior.webapp.request.CreateReviewReq;
import com.foodwastesavior.webapp.response.ApiResponse;
import com.foodwastesavior.webapp.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/review")
public class ReviewController {

    ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/createreview/{storeId}")
    public ResponseEntity<ApiResponse<String>> createReview(@PathVariable Integer storeId, @RequestBody CreateReviewReq createReviewReq, @RequestHeader("Authorization") String authorizationHeader) {
        String jwt = authorizationHeader.substring(7);

        String result = reviewService.createReview(storeId, createReviewReq, jwt);

        return ResponseEntity.ok(ApiResponse.success(201, result, result));
    }

}
