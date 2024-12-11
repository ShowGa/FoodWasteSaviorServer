package com.foodwastesavior.webapp.utils;

import com.foodwastesavior.webapp.exception.TokenValidationException;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.server.ResponseStatusException;

@Component
public class FirebaseHelper {
    public FirebaseUserInfo verifyToken(String idToken) {
        try {

            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);

            return new FirebaseUserInfo(
                    decodedToken.getEmail(),
                    decodedToken.getPicture(),
                    decodedToken.getName()
            );
        } catch (Exception e) {
            throw new TokenValidationException("Invalid or expired Firebase token", e);
        }


    }

    @Data
    @AllArgsConstructor
    public static class FirebaseUserInfo {
        private final String email;
        private final String avatarUrl;
        private final String username;
    }
}
