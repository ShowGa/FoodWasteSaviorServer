package com.foodwastesavior.webapp.controller;

import com.foodwastesavior.webapp.model.dto.UserDTO;
import com.foodwastesavior.webapp.repository.UserRepository;
import com.foodwastesavior.webapp.response.ApiResponse;
import com.foodwastesavior.webapp.service.UserService;
import com.google.j2objc.annotations.AutoreleasePool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping
    public ResponseEntity<ApiResponse<UserDTO>> updateUser(@RequestHeader("Authorization") String authorizationHeader, @RequestBody UserDTO userDTO) {
        String idToken = authorizationHeader.substring(7);

        UserDTO updatedUser = userService.updateUser(idToken, userDTO);

        return ResponseEntity.ok(ApiResponse.success(200, "用戶資料修改成功!", updatedUser));
    }

}
