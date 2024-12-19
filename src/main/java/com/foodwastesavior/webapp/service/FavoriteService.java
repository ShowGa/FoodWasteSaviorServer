package com.foodwastesavior.webapp.service;

import com.foodwastesavior.webapp.model.entity.Favorite;
import com.foodwastesavior.webapp.response.packageRes.FavoriteCardRes;

import java.util.List;

public interface FavoriteService {
    Boolean updateFavorite (String jwt, Integer packageId);

    List<FavoriteCardRes> getUserFavorites(String jwt, Integer userId);
}
