package com.foodwastesavior.webapp.service.impl;

import com.foodwastesavior.webapp.model.entity.User;
import com.foodwastesavior.webapp.repository.UserRepository;
import com.foodwastesavior.webapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

}

