package com.cinema.api.service.impl;

import com.cinema.api.model.UserModel;
import com.cinema.api.service.UserModelService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserModelServiceImpl implements UserModelService {

    @Value("${backend.server.url}")
    private String backend;

    private final String path = "/users/";

    @Override
    public UserModel getUserModelById(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backend + path + id, UserModel.class);
    }

    @Override
    public UserModel saveUserModel(UserModel userModel) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backend + path, userModel, UserModel.class).getBody();
    }

}
