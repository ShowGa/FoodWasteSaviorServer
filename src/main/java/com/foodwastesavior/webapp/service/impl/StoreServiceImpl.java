package com.foodwastesavior.webapp.service.impl;

import com.foodwastesavior.webapp.exception.NotFoundException;
import com.foodwastesavior.webapp.model.dto.StoreProfileDTO;
import com.foodwastesavior.webapp.model.entity.Address;
import com.foodwastesavior.webapp.model.entity.Store;
import com.foodwastesavior.webapp.repository.StoreRepository;
import com.foodwastesavior.webapp.response.storeResponse.SearchCardRes;
import com.foodwastesavior.webapp.response.storeResponse.StoreDetailRes;
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

    @Override
    public StoreDetailRes getStoreDetail(String jwt, Integer storeId) {
        // verify token first
        String subjectInfo = JwtUtil.validateToken(jwt);

        // get store
        Store foundStore = storeRepository.findById(storeId).orElseThrow(() -> new NotFoundException("糟糕!找不到這家商店資料"));

        Address foundAddress = foundStore.getAddress();

        StoreDetailRes storeDetailRes = new StoreDetailRes(foundStore.getStoreId(),
                foundStore.getName(),
                foundStore.getCoverImageUrl(),
                foundStore.getLogoImageUrl(),
                foundStore.getAbout(),
                foundStore.getRating(),
                foundStore.getRatingCount(),
                foundAddress.getAddressDetail());

        return storeDetailRes;
    }

    // ============ mystore =========== //
    @Override
    public StoreProfileDTO getStoreInfo(String jwt) {
        // verify token first
        String subjectInfo = JwtUtil.validateToken(jwt);

        // find store
        Store foundStore = storeRepository.findByEmail(subjectInfo).orElseThrow(() -> new NotFoundException("沒有找到店家相關資訊!"));

        return new StoreProfileDTO(foundStore.getName(), foundStore.getCoverImageUrl(), foundStore.getLogoImageUrl(), foundStore.getAbout());
    }

    @Override
    public StoreProfileDTO updateStoreInfo(StoreProfileDTO updatedInfo, String jwt) {
        // verify token first
        String subjectInfo = JwtUtil.validateToken(jwt);

        // find store
        Store foundStore = storeRepository.findByEmail(subjectInfo).orElseThrow(() -> new NotFoundException("沒有找到店家相關資訊!"));

        foundStore.setName(updatedInfo.getName());
        foundStore.setCoverImageUrl(updatedInfo.getCoverImageUrl());
        foundStore.setLogoImageUrl(updatedInfo.getLogoImageUrl());
        foundStore.setAbout(updatedInfo.getAbout());

        Store updatedStore = storeRepository.save(foundStore);

        // turn to dto
        return new StoreProfileDTO(updatedStore.getName(), updatedStore.getCoverImageUrl(), updatedStore.getLogoImageUrl(), updatedStore.getAbout());
    }
}

