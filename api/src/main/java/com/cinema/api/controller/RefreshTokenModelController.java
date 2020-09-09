package com.cinema.api.controller;

import com.cinema.api.model.RefreshTokenModel;
import com.cinema.api.model.UserModel;
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

    @PostMapping("/generate")
    private RefreshTokenModel generateRefreshToken(@RequestBody UserModel userModel) {
        return refreshTokenModelService.generateRefreshToken(userModel);
    }

    @PostMapping("/users")
    private RefreshTokenModel getRefreshTokenModelByUserModel(@RequestBody UserModel userModel) {
        return refreshTokenModelService.getRefreshTokenByUserModel(userModel);
    }

    @DeleteMapping("/{token}")
    private void deleteRefreshTokenModelByToken(@PathVariable String token) {
        refreshTokenModelService.deleteRefreshTokenByToken(token);
    }

}
