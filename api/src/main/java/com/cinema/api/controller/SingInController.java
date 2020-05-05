package com.cinema.api.controller;

import com.cinema.api.model.LoginModel;
import com.cinema.api.model.UserModel;
import com.cinema.api.security.jwt.JwtTokenProvider;
import com.cinema.api.service.LoginModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class SingInController {

    @Autowired
    private LoginModelService loginModelService;

    @Autowired
    private JwtTokenProvider jwt;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("")
    private String hiFunction() {
        return "Hi";
    }

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

            String token = jwt.createToken(username, userModel.getRole());
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("user", userModel);
            return ResponseEntity.ok(response);

        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
