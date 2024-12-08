package com.foodwastesavior.webapp.repository;

import com.foodwastesavior.webapp.model.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, Integer> {
    //
}


