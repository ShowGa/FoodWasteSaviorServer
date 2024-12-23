package com.foodwastesavior.webapp.service.impl;

import com.foodwastesavior.webapp.exception.NotFoundException;
import com.foodwastesavior.webapp.model.entity.Store;
import com.foodwastesavior.webapp.model.entity.User;
import com.foodwastesavior.webapp.repository.StoreRepository;
import com.foodwastesavior.webapp.repository.UserRepository;
import com.foodwastesavior.webapp.request.CreateReviewReq;
import com.foodwastesavior.webapp.service.ReviewService;
import com.foodwastesavior.webapp.utils.JwtUtil;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final StoreRepository storeRepo;
    private final UserRepository userRepo;

    public ReviewServiceImpl(StoreRepository storeRepo, UserRepository userRepo) {
        this.storeRepo = storeRepo;
        this.userRepo = userRepo;
    }

    @Override
    @Transactional
    public String createReview(Integer storeId, CreateReviewReq createReviewReq, String jwt) {
        // verify token
        String subjectInfo = JwtUtil.validateToken(jwt);

        // find store
        Store foundStore = storeRepo.findById(storeId).orElseThrow(() -> new NotFoundException("沒有找到相關資料，建立評價失敗!"));

        // find user
        User user = userRepo.findByEmail(subjectInfo).orElseThrow(() -> new NotFoundException("沒有找到相關資料，建立評價失敗!"));

        // calculate store rating , ratingCount, ratingSum
        Double currentSum = foundStore.getRatingSum() != null ? foundStore.getRatingSum() : 0.0;
        Integer currentCount =  foundStore.getRatingCount() != null ? foundStore.getRatingCount() : 0;

        currentSum += createReviewReq.getRating().doubleValue();
        currentCount += 1;

        Double newAverageRating = Math.round((currentSum / currentCount) * 10.0) / 10.0;

        foundStore.setRatingSum(currentSum);
        foundStore.setRatingCount(currentCount);
        foundStore.setRating(newAverageRating);

        storeRepo.save(foundStore);


        return "評價成功!";
    }
}
