package com.foodwastesavior.webapp.repository;

import com.foodwastesavior.webapp.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // Add later
    Optional<User> findByEmail(String email);
}
