package com.foodwastesavior.webapp.service.impl;

import com.foodwastesavior.webapp.exception.NotFoundException;
import com.foodwastesavior.webapp.model.entity.Favorite;
import com.foodwastesavior.webapp.model.entity.Package;
import com.foodwastesavior.webapp.model.entity.User;
import com.foodwastesavior.webapp.repository.FavoriteRepository;
import com.foodwastesavior.webapp.repository.PackageRepository;
import com.foodwastesavior.webapp.repository.UserRepository;
import com.foodwastesavior.webapp.service.FavoriteService;
import com.foodwastesavior.webapp.utils.JwtUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final UserRepository userRepository;
    private final PackageRepository packageRepository;

    @Autowired
    public FavoriteServiceImpl(FavoriteRepository favoriteRepository, UserRepository userRepository, PackageRepository packageRepository) {
        this.favoriteRepository = favoriteRepository;
        this.userRepository = userRepository;
        this.packageRepository = packageRepository;
    }

    @Transactional
    @Override
    public Boolean updateFavorite(String jwt, Integer packageId) {
        // Verify token and extract subject information
        String subjectInfo = JwtUtil.validateToken(jwt);

        // find user
        User foundUser = userRepository.findByEmail(subjectInfo).orElseThrow(() -> new NotFoundException("糟糕!沒有找到使用者資訊，無法更新最愛"));

        // find package
        Package foundPackage = packageRepository.findById(packageId).orElseThrow(() -> new NotFoundException("糟糕!沒有找到使用者資訊最愛"));

        // find favorite => check if was favorite
        Boolean isFavoriteExisted = favoriteRepository.existsByUserIdAndPackageId(foundUser.getUserId(), packageId);

        if (isFavoriteExisted) {
            favoriteRepository.deleteByUserIdAndPackageId(foundUser.getUserId(), packageId);

            return false;
        } else {
            Favorite newFavorite = new Favorite();
            newFavorite.setUser(foundUser);
            newFavorite.setPack(foundPackage);
            favoriteRepository.save(newFavorite);

            return true;
        }
    }
}
