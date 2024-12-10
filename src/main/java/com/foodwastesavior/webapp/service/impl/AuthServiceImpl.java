package com.foodwastesavior.webapp.service.impl;

import com.foodwastesavior.webapp.model.entity.Address;
import com.foodwastesavior.webapp.model.entity.User;
import com.foodwastesavior.webapp.repository.UserRepository;
import com.foodwastesavior.webapp.response.LoginResponse;
import com.foodwastesavior.webapp.security.JwtUtil;
import com.foodwastesavior.webapp.service.AuthService;
import com.foodwastesavior.webapp.service.UserService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
    }

    @Override
    public LoginResponse googleOAuth(String idToken) throws FirebaseAuthException {
        /* ======= verify the token first from client first ====== */
        // check if the token is there (Do Later)

        // decoded the token with firebase admin sdk


        // verify token to firebase server and extract info
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
        String email = decodedToken.getEmail();
        String avatar = decodedToken.getPicture();
        String username = decodedToken.getName();

        // ======= OAuth User login Logic =======//
        Optional<User> foundedUserOpt = userRepository.findByEmail(email);

        if (foundedUserOpt.isEmpty()) {
            User savedUser = createAndSaveUserGoogleOAuth(email, avatar, username);

            // sign token
            String jwtToken = JwtUtil.generateToken(savedUser.getEmail(), 30);

            // Create

            return new LoginResponse(jwtToken, savedUser.getUserId(), savedUser.getEmail(), savedUser.getAvatarUrl());

        } else {
            String jwtToken = JwtUtil.generateToken(foundedUserOpt.get().getEmail(), 30);

            return new LoginResponse(jwtToken, foundedUserOpt.get().getUserId(), foundedUserOpt.get().getEmail(), foundedUserOpt.get().getAvatarUrl());

        }
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
