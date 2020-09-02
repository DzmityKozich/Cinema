package com.cinema.api.controller;

import com.cinema.api.model.RefreshTokenModel;
import com.cinema.api.service.impl.RefreshTokenModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/refresh-token")
public class RefreshTokenModelController {

    @Autowired
    private RefreshTokenModelServiceImpl refreshTokenModelService;

    @GetMapping("/{token}")
    private RefreshTokenModel getRefreshTokenModelByToken(@PathVariable String token) {
        return refreshTokenModelService.getRefreshTokenByToken(token);
    }

    @GetMapping("/generate")
    private RefreshTokenModel generateRefreshToken() {
        return refreshTokenModelService.generateRefreshToken();
    }

    @DeleteMapping("/{token}")
    private void deleteRefreshTokenModelByToken(@PathVariable String token) {
        refreshTokenModelService.deleteRefreshTokenByToken(token);
    }

}
