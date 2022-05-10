package com.usta.p2t4_jwt.util;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


@Component
public class JWTUtility implements Serializable {
    private static final Long serialVersionUID = 234234523523L;

    public static final Long JWT_VALIDITY = Long.valueOf(2 * 60 * 60);

    @Value("${jwt.secret}")
    private String secretKey;

    /**
     * The function getUsernameFromToken() takes a token as a parameter and returns the username from
     * the token
     * 
     * @param token The JWT token
     * @return The subject of the token.
     */
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    /**
     * The function getClaimFromToken() is called with the token and a lambda expression that returns
     * the expiration date from the claims object
     * 
     * @param token The JWT token
     * @return The expiration date of the token.
     */
    public Date getExpDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    /**
     * It takes a token and a function as input and returns the result of applying the function to the
     * claims of the token
     * 
     * @param token The JWT token
     * @param claimsResolver A function that takes in a Claims object and returns a value of type T.
     * @return The claimsResolver.apply(claims) is being returned.
     */
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    /**
     * It takes a token and returns the claims
     * 
     * @param token The token that you want to validate.
     * @return The JWT token is being returned.
     */
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    /**
     * If the expiration date of the token is before the current date, then the token is expired
     * 
     * @param token The JWT token
     * @return A boolean value.
     */
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * The function takes a userDetails object and returns a token
     * 
     * @param userDetails This is the user object that contains the user information.
     * @return A token
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }

    /**
     * It takes a map of claims and a subject and returns a JWT token
     * 
     * @param claims A map of claims that will be added to the JWT.
     * @param subject The subject of the token.
     * @return A JWT token.
     */
    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS256, secretKey).compact();
    }

    /**
     * If the username in the token matches the username in the userDetails object and the token is not
     * expired, then return true
     * 
     * @param token The JWT token to validate
     * @param userDetails The user details object that contains the username and password of the user.
     * @return A boolean value.
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public static Long getSerialversionuid() {
        return serialVersionUID;
    }
}
