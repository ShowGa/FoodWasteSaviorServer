package com.foodwastesavior.webapp.service.impl;

import com.foodwastesavior.webapp.exception.NotFoundException;
import com.foodwastesavior.webapp.exception.TokenValidationException;
import com.foodwastesavior.webapp.model.dto.UserDTO;
import com.foodwastesavior.webapp.model.entity.User;
import com.foodwastesavior.webapp.repository.UserRepository;
import com.foodwastesavior.webapp.service.UserService;
import com.foodwastesavior.webapp.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO updateUser(String jwt, UserDTO userDTO) {
        // Verify token and extract subject information
        String subjectInfo = JwtUtil.validateToken(jwt);
        if (!Objects.equals(subjectInfo, userDTO.getEmail())) {
            throw new TokenValidationException("身分驗證失敗!請重新登入。");
        }

        // update user information
        User foundUser = userRepository.findByEmail(userDTO.getEmail()).orElseThrow(() -> new NotFoundException("糟糕!沒有找到您的資料，無法修改!"));

        foundUser.setName(userDTO.getUsername());
        foundUser.setEmail(userDTO.getEmail());
        foundUser.setAvatarUrl(userDTO.getAvatarUrl());

        User saveUser = userRepository.save(foundUser);

        UserDTO updateUser = new UserDTO(saveUser.getUserId(), saveUser.getName(),saveUser.getEmail(), saveUser.getAvatarUrl());

        return updateUser;
    }
}

