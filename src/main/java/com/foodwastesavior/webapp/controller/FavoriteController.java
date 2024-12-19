package com.foodwastesavior.webapp.controller;

import com.foodwastesavior.webapp.response.ApiResponse;
import com.foodwastesavior.webapp.response.packageRes.FavoriteCardRes;
import com.foodwastesavior.webapp.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/favorite")
public class FavoriteController {

    FavoriteService favoriteService;

    @Autowired
    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @PutMapping("/update/{packageId}")
    public ResponseEntity<ApiResponse<Boolean>> updateFavorite(@PathVariable Integer packageId, @RequestHeader("Authorization") String authorizationHeader) {
        String jwt = authorizationHeader.substring(7);

        Boolean isFavorite = favoriteService.updateFavorite(jwt, packageId);

        return ResponseEntity.ok(ApiResponse.success(200, "修改最愛成功!", isFavorite));
    }

    @GetMapping("/getuserfavorite/{userId}")
    public ResponseEntity<ApiResponse<List<FavoriteCardRes>>> getUserFavorites
            (@PathVariable Integer userId, @RequestHeader("Authorization") String authorizationHeader) {

        String jwt = authorizationHeader.substring(7);

        // find FavoriteCard
        List<FavoriteCardRes> results = favoriteService.getUserFavorites(jwt, userId);

        if (results.isEmpty()) {
            return ResponseEntity.ok(ApiResponse.success(204, "您還沒有收藏喔!趕快去看看吧!", results));
        }

        return ResponseEntity.ok(ApiResponse.success(204, "找到您的收藏囉!", results));
    }

}
