package com.foodwastesavior.webapp.repository;

import com.foodwastesavior.webapp.model.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
    //
}

