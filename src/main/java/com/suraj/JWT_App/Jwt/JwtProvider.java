package com.suraj.JWT_App.Jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long expirationTime;

    // Generate JWT Token based on the username
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)  // Set the subject (username) in the JWT payload
                .setIssuedAt(new Date()) // Set the issue date as the current date
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime)) // Set expiration time
                .signWith(SignatureAlgorithm.HS256, secretKey) // Sign the JWT with HS256 algorithm and secret key
                .compact(); // Finalize and compact the JWT token
    }

    // Extract the username (subject) from the JWT token
    public String extractUsername(String jwtToken) {
        return extractClaim(jwtToken, Claims::getSubject); // Get the subject from the JWT token (username)
    }

    // Extract claims from the JWT token
    private <T> T extractClaim(String token, ClaimsExtractor<T> claimsExtractor) {
        final Claims claims = extractAllClaims(token); // Get all claims
        return claimsExtractor.extract(claims); // Extract specific claim from the claims
    }

    // Extract all claims from the JWT token
    private Claims extractAllClaims(String token) {
        return (Claims) Jwts.parser()
                .setSigningKey(secretKey)
                .build()
                .parse(token)
                .getBody(); // Parse JWT and get the claims
    }

    // Validate if the JWT token is valid for the user
    public boolean validateToken(String jwtToken, UserDetails userDetails) {
        final String username = extractUsername(jwtToken); // Extract the username from the token
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(jwtToken)); // Validate username and expiration
    }

    // Check if the JWT token is expired
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date()); // Compare expiration date with current date
    }

    // Extract the expiration date from the JWT token
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration); // Extract the expiration claim from the token
    }

    // Functional interface for extracting claims
    private interface ClaimsExtractor<T> {
        T extract(Claims claims);
    }
}
