package com.foodwastesavior.webapp.service;

import com.foodwastesavior.webapp.model.entity.Store;
import com.foodwastesavior.webapp.response.storeResponse.SearchCardRes;


import java.util.List;

public interface StoreService {
    List<SearchCardRes> findStoreWithDistance(String jwt, Double longitude, Double latitude,Integer radius);
}