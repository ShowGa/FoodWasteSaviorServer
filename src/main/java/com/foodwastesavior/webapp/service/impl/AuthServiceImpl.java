package com.foodwastesavior.webapp.service.impl;

import com.foodwastesavior.webapp.model.entity.Address;
import com.foodwastesavior.webapp.model.entity.User;
import com.foodwastesavior.webapp.repository.UserRepository;
import com.foodwastesavior.webapp.response.LoginResponse;
import com.foodwastesavior.webapp.utils.FirebaseHelper;
import com.foodwastesavior.webapp.utils.FirebaseHelper.FirebaseUserInfo;
import com.foodwastesavior.webapp.utils.JwtUtil;
import com.foodwastesavior.webapp.service.AuthService;
import com.foodwastesavior.webapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final FirebaseHelper firebaseHelper;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, UserService userService, FirebaseHelper firebaseHelper) {
        this.userRepository = userRepository;
        this.firebaseHelper = firebaseHelper;
    }

    @Override
    public LoginResponse googleOAuth(String idToken)  {
        /* ======= verify the token first from client first ====== */

        // decoded the token with firebase admin sdk
        // verify token to firebase server and extract info
        FirebaseUserInfo userInfo = firebaseHelper.verifyToken(idToken);

        // ======= OAuth User login Logic =======//

        // not found than generate new user
        User user = userRepository.findByEmail(userInfo.getEmail())
                .orElseGet(() -> createAndSaveUserGoogleOAuth(
                        userInfo.getEmail(),
                        userInfo.getAvatarUrl(),
                        userInfo.getUsername()
                ));


        String jwtToken = JwtUtil.generateToken(user.getEmail(), 30);

        return new LoginResponse(jwtToken, user.getUserId(), user.getEmail(), user.getAvatarUrl());
    }

    /* =============== private method =============== */
    private User createAndSaveUserGoogleOAuth(String email, String avatar, String username) {
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setAvatarUrl(avatar);
        newUser.setName(username);

        Address newAddress = new Address();
        newAddress.setLatitude(25.033000);
        newAddress.setLongitude(121.565400);
        newAddress.setType(Address.AddressType.USER);

        newUser.setAddress(newAddress);

        return userRepository.save(newUser);
    }
}
