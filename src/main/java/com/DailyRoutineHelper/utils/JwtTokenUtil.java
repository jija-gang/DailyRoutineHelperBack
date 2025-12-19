package com.DailyRoutineHelper.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.SignatureException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class JwtTokenUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    private final SignatureAlgorithm algorithm = SignatureAlgorithm.HS512;

    public String generateToken(Long userId, String role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);
        claims.put("userId", userId);
        log.warn(claims.toString());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(String.valueOf(userId))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(algorithm, secret)
                .compact();
    }

    public Claims extractAllClaims(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            log.warn("Token expired: {}", e.getMessage());
            return e.getClaims(); // все равно возвращаем claims для информации
        }
    }

    public Long extractUserId(String token) {
        try {
            Claims claims = extractAllClaims(token);
            String subject = claims.getSubject();
            if (subject != null) {
                return Long.parseLong(subject);
            }
            Object userId = claims.get("userId");
            if (userId instanceof Number) {
                return ((Number) userId).longValue();
            }
            return Long.parseLong(userId.toString());
        } catch (Exception e) {
            log.error("Failed to extract user ID from token", e);
            throw new IllegalArgumentException("Invalid token");
        }
    }

    public String extractRole(String token) {
        try {
            Claims claims = extractAllClaims(token);
            Object role = claims.get("role");
            return role != null ? role.toString() : "USER";
        } catch (Exception e) {
            log.error("Failed to extract role from token", e);
            return "USER";
        }
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            log.warn("Token expired: {}", e.getMessage());
            return false;
        } catch (MalformedJwtException e) {
            log.warn("Invalid token format: {}", e.getMessage());
            return false;
        } catch (Exception e) {
            log.warn("Token validation error: {}", e.getMessage());
            return false;
        }
    }
}