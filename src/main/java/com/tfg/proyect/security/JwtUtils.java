package com.tfg.proyect.security;

import com.tfg.proyect.model.EmployeeEntity;
import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * The type Jwt utils.
 */
@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${tfg.app.jwtSecret}")
    private String jwtSecret;

    @Value("${tfg.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    @Value("${tfg.app.jwtCookieName}")
    private String jwtCookie;


    /**
     * Gets jwt from cookies.
     *
     * @param request the request
     * @return the jwt from cookies
     */
    public String getJwtFromCookies(HttpServletRequest request) {
        if (request.getHeader("x-auth-token") == null) {
            return null;
        }
        return request.getHeader("x-auth-token").split(";")[0].split("=")[1];
    }

    /**
     * Generate jwt cookie response cookie.
     *
     * @param userPrincipal the user principal
     * @return the response cookie
     */
    public ResponseCookie generateJwtCookie(EmployeeEntity userPrincipal) {
        String jwt = generateTokenFromUsername(userPrincipal.getUsername());
        return ResponseCookie.from(jwtCookie, jwt).path("/").maxAge(33000).httpOnly(false).build();
    }

    /**
     * Gets clean jwt cookie.
     *
     * @return the clean jwt cookie
     */
    public ResponseCookie getCleanJwtCookie() {
        return ResponseCookie.from(jwtCookie, "").path("/").build();
    }

    /**
     * Gets user name from jwt token.
     *
     * @param token the token
     * @return the user name from jwt token
     */
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    /**
     * Validate jwt token boolean.
     *
     * @param authToken the auth token
     * @return the boolean
     */
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }

    /**
     * Generate token from username string.
     *
     * @param username the username
     * @return the string
     */
    public String generateTokenFromUsername(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
}
