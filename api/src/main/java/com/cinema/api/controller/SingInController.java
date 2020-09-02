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
import org.springframework.web.bind.annotation.*;

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
            response.put("refreshToken", refreshTokenModelService.generateRefreshToken());
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

    @PostMapping("/refresh-token")
    private ResponseEntity refreshToken(@RequestBody JwtRefreshToken token) {
        if (refreshTokenModelService.getRefreshTokenByToken(token.getRefreshToken()) != null) {
            String jwtToken = jwt.createToken(token.getUserModel().getFirstName(), token.getUserModel().getRole());
            String refreshToken = refreshTokenModelService.generateRefreshToken().getToken();
            Map<String, Object> response = new HashMap<>();
            response.put("jwtToken", jwtToken);
            response.put("refreshToken", refreshToken);
            return ResponseEntity.ok(response);
        } else return ResponseEntity.badRequest().body("Invalid Refresh Token!");
    }
}
