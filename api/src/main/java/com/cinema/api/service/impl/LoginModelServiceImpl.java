package com.cinema.api.service.impl;

import com.cinema.api.model.LoginModel;
import com.cinema.api.model.UserModel;
import com.cinema.api.service.LoginModelService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LoginModelServiceImpl implements LoginModelService {

    @Value("${backend.server.url}")
    private String backend;

    private final String path = "/logins";

    @Override
    public UserModel getUserModelByLoginModel(String email) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backend + path + "/email/" + email, UserModel.class);
    }

    @Override
    public LoginModel saveLoginModel(LoginModel login) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backend + path, login, LoginModel.class).getBody();
    }
}
