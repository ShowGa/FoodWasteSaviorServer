package com.foodwastesavior.webapp.utils;

import com.foodwastesavior.webapp.exception.TokenValidationException;
import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {

    // set into the safe place
    // need 32 characters
    private static final String SECRET_KEY = Dotenv.load().get("SECRET_KEY_JWT");

    private static Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    // expiration => days
    public static String generateToken(String subject, long expirationTime) {
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + (24 * 60 * 60 * 1000 * expirationTime)))
                .signWith(getSigningKey())
                .compact();
    }

    ////////// add exception later
    public static String validateToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            return claims.getSubject();

        } catch (JwtException e) {
            throw new TokenValidationException("Invalid or expired Jwt Token", e);
        }

    }
}
