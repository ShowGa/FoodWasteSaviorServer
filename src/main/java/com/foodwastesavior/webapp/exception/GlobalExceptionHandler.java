package com.foodwastesavior.webapp.exception;

import com.foodwastesavior.webapp.response.ApiResponse;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuthException;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    /* ============= Global Exception ============= */


    /* ============== Other exception ============== */
    @ExceptionHandler(TokenValidationException.class)
    public ResponseEntity<ApiResponse<String>> handleTokenValidationException (TokenValidationException e) {

        HttpStatus status = HttpStatus.FORBIDDEN;

        ApiResponse<String> res = ApiResponse.error(status.value(), e.getMessage());

        return ResponseEntity.status(status).body(res);
    }

    @ExceptionHandler(FirebaseAuthException.class)
    public ResponseEntity<ApiResponse<String>> handleFirebaseException() {

        HttpStatus status = HttpStatus.FORBIDDEN;

        ApiResponse<String> res = ApiResponse.error(status.value(), "Token Invalid !");

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(res);
    }

}
