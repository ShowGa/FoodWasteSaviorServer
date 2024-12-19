package com.foodwastesavior.webapp.controller;

import com.foodwastesavior.webapp.response.ApiResponse;
import com.foodwastesavior.webapp.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

}
