package com.example.mess.bhojanalaya.Configuration;

import com.example.mess.bhojanalaya.Model.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.util.WebUtils;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.logging.Logger;

@Service
public class JwtService {

    @Value("${spring.app.jwtSecretKey}")
    private String jwtSecretKey;

    @Value("${spring.app.jwtExpiration}")
    private int jwtExpiration;

    @Value("{spring.app.jwtCookieName}")
    private String jwtCookie;

    public String getJwtFromHeader(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public ResponseCookie generateJwtCookie(UserDetails userDetails){
        String jwt = generateTokenFromUserName(userDetails.getUsername());
        ResponseCookie cookie = ResponseCookie.from(jwtCookie,jwt)
                .path("/api")
                .maxAge(24*60*60)
                .httpOnly(false)
                .secure(false)
                .build();
        return cookie;

    }

    public String getJwtFromCookie(HttpServletRequest request){
        Cookie cookie = WebUtils.getCookie(request,jwtCookie);
        if (cookie != null) {
            return cookie.getValue();
        }
        return null;
    }

    public String generateTokenFromUserName(String username){
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + jwtExpiration))
                .signWith(key())
                .compact();
    }

    public String getUserNameFromJwtToken(String token){
        return Jwts.parser()
                .verifyWith((SecretKey)key())
                .build().parseSignedClaims(token)
                .getPayload().getSubject();
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecretKey));
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().verifyWith((SecretKey) key()).build().parseSignedClaims(authToken);
            return true;
        } catch (MalformedJwtException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

//    public String extractUsername(String token) {
//        // Logic to extract username from JWT token
//        // This is a placeholder implementation
//        return extractClaim(token, Claims::getSubject);
//    }
//
//    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
//        final Claims claims = extractAllClaims(token);
//        return claimsResolver.apply(claims);
//    }
//
//    private Claims extractAllClaims(String token){
//        // Logic to extract all claims from JWT token
//        // This is a placeholder implementation
//        return Jwts
//                .parser()
//                .setSigningKey(getSignInKey())
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//    }
//
//    private Key getSignInKey() {
//        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
//        return Keys.hmacShaKeyFor(keyBytes);
//    }
//
//    public String generateToken(User user) {
//        return  generateToken(new HashMap<>(),user);
//    }
//
//    public String generateToken(Map<String,Object> extraClaims, User user){
//        return Jwts
//                .builder()
//                .setClaims(extraClaims)
//                .setSubject(user.getEmail())
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis()+ 1000 * 60 * 24 * 60))
//                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
//                .compact();
//    }
//
//    public boolean isTokenValid(String token , UserDetails userDetails) {
//        final String username = extractUsername(token);
//        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//    }
//
//    private boolean isTokenExpired(String token) {
//        return extractExpiration(token).before(new Date());
//    }
//
//    private Date extractExpiration(String token) {
//        return extractClaim(token, Claims::getExpiration);
//    }
}

