package com.foodwastesavior.webapp.service.impl;

import com.foodwastesavior.webapp.repository.FavoriteRepository;
import com.foodwastesavior.webapp.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    private final FavoriteRepository favoriteRepository;

    @Autowired
    public FavoriteServiceImpl(FavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }
}
