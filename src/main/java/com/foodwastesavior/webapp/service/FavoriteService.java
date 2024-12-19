package com.foodwastesavior.webapp.service;

import com.foodwastesavior.webapp.model.entity.Favorite;
import java.util.List;

public interface FavoriteService {
    Boolean updateFavorite (String jwt, Integer packageId);
}
