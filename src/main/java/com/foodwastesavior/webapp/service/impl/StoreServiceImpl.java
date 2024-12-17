package com.foodwastesavior.webapp.service.impl;

import com.foodwastesavior.webapp.model.entity.Store;
import com.foodwastesavior.webapp.repository.StoreRepository;
import com.foodwastesavior.webapp.response.storeResponse.SearchCardRes;
import com.foodwastesavior.webapp.service.StoreService;
import com.foodwastesavior.webapp.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;

    @Autowired
    public StoreServiceImpl(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Override
    public List<SearchCardRes> findStoreWithDistance(String jwt, Double longitude, Double latitude, Integer radius) {
        // verify token first
//        String subjectInfo = JwtUtil.validateToken(jwt);

        // find the store with distance
        List<Object[]> results = storeRepository.findStoresWithinDistance(longitude, latitude, Double.valueOf(radius));

        return results.stream()
                .map(row -> new SearchCardRes(
                        ((Number) row[0]).intValue(),   // storeId
                        (String) row[1],               // storeCoverImage
                        (String) row[2],               // storeName
                        (String) row[3],               // storeAddress
                        ((Number) row[4]).doubleValue(), // longitude
                        ((Number) row[5]).doubleValue(), // latitude
                        ((Number) row[6]).doubleValue(), // rating
                        ((Number) row[7]).doubleValue()  // distance
                ))
                .collect(Collectors.toList());
    }
}

