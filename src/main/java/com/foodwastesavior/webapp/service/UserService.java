package com.foodwastesavior.webapp.service;

import com.foodwastesavior.webapp.model.dto.UserDTO;
import com.foodwastesavior.webapp.model.entity.User;
import java.util.List;

public interface UserService {
    UserDTO updateUser(String jwt, UserDTO userDTO);
}

