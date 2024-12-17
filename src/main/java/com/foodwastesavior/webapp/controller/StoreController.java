package com.foodwastesavior.webapp.controller;

import com.foodwastesavior.webapp.response.ApiResponse;
import com.foodwastesavior.webapp.response.storeResponse.SearchCardRes;
import com.foodwastesavior.webapp.response.storeResponse.StoreDetailRes;
import com.foodwastesavior.webapp.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/stores")
public class StoreController {

    StoreService storeService;

    @Autowired
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping("/findstorewithdistance")
    public ResponseEntity<ApiResponse<List<SearchCardRes>>> findStoreWithDistance(
                @RequestParam Double longitude,
                @RequestParam Double latitude,
                @RequestParam Integer radius,
                @RequestHeader("Authorization") String authorizationHeader
            ) {
        String jwt = authorizationHeader.substring(7);

        List<SearchCardRes> searchCardRes = storeService.findStoreWithDistance(jwt, longitude, latitude, radius);

        return ResponseEntity.ok(ApiResponse.success(200, "Find Store Successfully !", searchCardRes));
    }

    @GetMapping("/storedetail/{storeId}")
    public ResponseEntity<ApiResponse<StoreDetailRes>> getStoreDetail(
            @PathVariable Integer storeId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        String jwt = authorizationHeader.substring(7);
        System.out.println("storedetail : " + jwt);

        StoreDetailRes storeDetailRes = storeService.getStoreDetail(jwt, storeId);

        return ResponseEntity.ok(ApiResponse.success(200, "Find Store Successfully !", storeDetailRes));
    }


}
