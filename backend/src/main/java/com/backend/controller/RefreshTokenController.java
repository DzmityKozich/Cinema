package com.backend.controller;

import com.backend.entity.RefreshToken;
import com.backend.entity.User;
import com.backend.service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/refresh-token")
public class RefreshTokenController {

    @Autowired
    private RefreshTokenService refreshTokenService;

    @GetMapping("/{token}")
    private RefreshToken getRefreshTokenByToken(@PathVariable String token) {
        return refreshTokenService.getRefreshTokenByToken(token);
    }

    @PostMapping("/users")
    private RefreshToken getByUser(@RequestBody User user) {
        return refreshTokenService.getByUser(user);
    }

    @PostMapping("/generate")
    private RefreshToken generateRefreshToken(@RequestBody User user) {
        return refreshTokenService.generateRefreshToken(user);
    }

    @DeleteMapping("/{token}")
    private void deleteRefreshTokenByToken(@PathVariable String token) {
        refreshTokenService.deleteRefreshTokenByToken(token);
    }

}
