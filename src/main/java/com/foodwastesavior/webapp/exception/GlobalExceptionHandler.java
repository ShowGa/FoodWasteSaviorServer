package com.foodwastesavior.webapp.exception;

import com.foodwastesavior.webapp.response.ApiResponse;
import com.google.api.Http;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuthException;
import io.jsonwebtoken.JwtException;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    /* ============= Common Exception ============= */


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

    @ExceptionHandler(ExistedException.class)
    public ResponseEntity<ApiResponse<String>> handleExistedException (ExistedException e) {
        HttpStatus status = HttpStatus.CONFLICT;

        ApiResponse<String> res = ApiResponse.error(status.value(), e.getMessage());

        return ResponseEntity.status(status).body(res);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleNotFoundException (ExistedException e) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        ApiResponse<String> res = ApiResponse.error(status.value(), e.getMessage());

        return ResponseEntity.status(status).body(res);
    }

}
