package com.foodwastesavior.webapp.service.impl;

import com.foodwastesavior.webapp.model.entity.Store;
import com.foodwastesavior.webapp.repository.StoreRepository;
import com.foodwastesavior.webapp.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;

    @Autowired
    public StoreServiceImpl(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }
}

