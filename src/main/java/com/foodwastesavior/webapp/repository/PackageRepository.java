package com.foodwastesavior.webapp.repository;


import com.foodwastesavior.webapp.model.entity.Package;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackageRepository extends JpaRepository<Package, Integer> {
    //
}

