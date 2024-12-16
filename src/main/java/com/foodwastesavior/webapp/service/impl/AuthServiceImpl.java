package com.foodwastesavior.webapp.service.impl;

import com.foodwastesavior.webapp.model.entity.Address;
import com.foodwastesavior.webapp.model.entity.User;
import com.foodwastesavior.webapp.repository.UserRepository;
import com.foodwastesavior.webapp.response.AddressRes;
import com.foodwastesavior.webapp.response.LoginResponse;
import com.foodwastesavior.webapp.response.UserPositionRes;
import com.foodwastesavior.webapp.utils.FirebaseHelper;
import com.foodwastesavior.webapp.utils.FirebaseHelper.FirebaseUserInfo;
import com.foodwastesavior.webapp.utils.JwtUtil;
import com.foodwastesavior.webapp.service.AuthService;
import com.foodwastesavior.webapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
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

        // set addressres
        Address address = user.getAddress();
        UserPositionRes userPositionRes = new UserPositionRes();

//        System.out.println(address);

        userPositionRes.setLatitude(address.getLatitude());
        userPositionRes.setLongitude(address.getLongitude());


        String jwtToken = JwtUtil.generateToken(user.getEmail(), 30);

        return new LoginResponse(jwtToken, user.getUserId(), user.getName(),user.getEmail(), user.getAvatarUrl(), userPositionRes);
    }

    /* =============== private method =============== */
    private User createAndSaveUserGoogleOAuth(String email, String avatar, String username) {
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setAvatarUrl(avatar);
        newUser.setName(username);

        Address newAddress = new Address();
        newAddress.setAddressDetail("台北市信義區信義路五段7號");
        newAddress.setCountry("台灣");
        newAddress.setCity("台北市");
        newAddress.setPostalCode(110);
        newAddress.setLatitude(25.033000);
        newAddress.setLongitude(121.565400);
        newAddress.setType(Address.AddressType.USER);
        newAddress.setUser(newUser);

        newUser.setAddress(newAddress);

        return userRepository.save(newUser);
    }
}
