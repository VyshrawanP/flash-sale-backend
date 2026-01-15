package com.FlashSaleApllication.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    // IMPORTANT:
    // For HS256, secret must be at least 32 bytes
    private static final String SECRET =
            "this_is_a_very_secret_key_which_is_at_least_32_bytes_long";

    private static final long EXPIRATION_TIME = 60 * 60 * 1000; // 1 hour

    /**
     * Generate JWT token with userId as subject
     */
    public String generateToken(Long userId) {
        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(Keys.hmacShaKeyFor(SECRET.getBytes()))
                .compact();
    }

    /**
     * Extract userId from JWT token
     */
    public Long extractUserId(String token) {
        return Long.parseLong(
                Jwts.parserBuilder()
                        .setSigningKey(SECRET.getBytes())
                        .build()
                        .parseClaimsJws(token)
                        .getBody()
                        .getSubject()
        );
    }
}
