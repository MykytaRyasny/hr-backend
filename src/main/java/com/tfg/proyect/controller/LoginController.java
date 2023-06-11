package com.tfg.proyect.controller;

import com.tfg.proyect.dto.LoginDTO;
import com.tfg.proyect.dto.UserInfoResponse;
import com.tfg.proyect.model.EmployeeEntity;
import com.tfg.proyect.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.tfg.proyect.controller.utils.LoggerUtils.LoggerInfo;

/**
 * The type Login controller.
 */
@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * The Jwt utils.
     */
    @Autowired
    JwtUtils jwtUtils;

    /**
     * Authenticate user response entity.
     *
     * @param loginRequest the login request
     * @return the userDetail which I use to filter in FrontEnd
     */
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Validated @RequestBody LoginDTO loginRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        EmployeeEntity userDetails = (EmployeeEntity) authentication.getPrincipal();

        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        String className = new Object() {
        }.getClass().getEnclosingClass().getName();
        LoggerInfo(methodName, className);
        return ResponseEntity.ok().header("x-auth-token", jwtCookie.toString())
                .body(new UserInfoResponse(
                        userDetails.getFirstName(),
                        userDetails.getLastName(),
                        userDetails.getUsername(),
                        userDetails.getRole()));
    }

    /**
     * Logout user response entity.
     *
     * @return idealy delete the cookie
     */
    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        String className = new Object() {
        }.getClass().getEnclosingClass().getName();
        LoggerInfo(methodName, className);
        return ResponseEntity
                .ok()
                .header("x-auth-token", cookie.toString())
                .body(null);
    }


}
