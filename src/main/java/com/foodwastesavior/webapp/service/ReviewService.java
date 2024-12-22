package com.foodwastesavior.webapp.service;

import com.foodwastesavior.webapp.request.CreateReviewReq;

public interface ReviewService {
    String createReview(Integer storeId, CreateReviewReq createReviewReq, String jwt);
}
