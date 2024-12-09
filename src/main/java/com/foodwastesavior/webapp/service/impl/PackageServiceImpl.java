package com.foodwastesavior.webapp.service.impl;

import com.foodwastesavior.webapp.model.entity.Package;
import com.foodwastesavior.webapp.repository.PackageRepository;
import com.foodwastesavior.webapp.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackageServiceImpl implements PackageService {

    private final PackageRepository packageRepository;

    // constructor injection
    @Autowired
    public PackageServiceImpl(PackageRepository packageRepository) {
        this.packageRepository = packageRepository;
    }
}
