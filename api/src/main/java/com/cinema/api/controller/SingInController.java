package com.cinema.api.controller;

import com.cinema.api.model.LoginModel;
import com.cinema.api.model.UserModel;
import com.cinema.api.security.jwt.JwtRefreshToken;
import com.cinema.api.security.jwt.JwtTokenProvider;
import com.cinema.api.service.LoginModelService;
import com.cinema.api.service.RefreshTokenModelService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/login")
public class SingInController {

    @Autowired
    private LoginModelService loginModelService;

    @Autowired
    private JwtTokenProvider jwt;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RefreshTokenModelService refreshTokenModelService;

    @PostMapping("")
    private ResponseEntity login(@RequestBody LoginModel loginModel) {
        try {
            String username = loginModel.getEmail();
            UserModel userModel = loginModelService.getUserModelByEmail(username);

            if (userModel == null) {
                throw new UsernameNotFoundException("User: " + username + " not found!");
            }

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    username,
                    loginModel.getPassword()
            );
            authenticationManager.authenticate(authentication);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwt.createToken(username, userModel.getRole());
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("currentUser", userModel);
            response.put("refreshToken", refreshTokenModelService.generateRefreshToken().getToken());
            return ResponseEntity.ok(response);

        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @PostMapping("/relogin")
    private ResponseEntity reLogin(@RequestBody String token) {

        if (token != null) {
            Claims claims = jwt.parseToken(token);
            String username = claims.getSubject();
            UserModel currentUser = loginModelService.getUserModelByEmail(username);
            return ResponseEntity.ok(currentUser);
        } else return ResponseEntity.badRequest().body("Token is null");

    }

//    @Transactional
    @PostMapping("/refresh-token") // "/refresh/token"
    private ResponseEntity refreshToken(@RequestBody JwtRefreshToken token) {
        if (refreshTokenModelService.getRefreshTokenByToken(token.getRefreshToken()) != null) {
            String username = loginModelService.getEmailByUser(token.getUserModel());
            String jwtToken = jwt.createToken(username, token.getUserModel().getRole());
            String refreshToken = refreshTokenModelService.generateRefreshToken().getToken();
            Map<String, Object> response = new HashMap<>();
            response.put("token", jwtToken);
            response.put("refreshToken", refreshToken);
            refreshTokenModelService.deleteRefreshTokenByToken(token.getRefreshToken());
            System.out.println("User " +  username + " got a new token!");
            return ResponseEntity.ok(response);
        } else return ResponseEntity.badRequest().body("Invalid Refresh Token!");
    }
}
