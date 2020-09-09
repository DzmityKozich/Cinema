package com.cinema.api.service.impl;

import com.cinema.api.model.RefreshTokenModel;
import com.cinema.api.model.UserModel;
import com.cinema.api.service.RefreshTokenModelService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RefreshTokenModelServiceImpl implements RefreshTokenModelService {

    @Value("${backend.server.url}")
    private String backend;
    private final String path = "/refresh-token";

    @Override
    public RefreshTokenModel getRefreshTokenByToken(String token) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backend + path + "/" + token, RefreshTokenModel.class);
    }

    @Override
    public RefreshTokenModel getRefreshTokenByUserModel(UserModel userModel) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backend + path + "/users", userModel, RefreshTokenModel.class).getBody();
    }

    @Override
    public RefreshTokenModel generateRefreshToken(UserModel userModel) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backend + path + "/generate", userModel, RefreshTokenModel.class).getBody();
    }

    @Override
    public void deleteRefreshTokenByToken(String token) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backend + path + "/" + token);
    }
}
