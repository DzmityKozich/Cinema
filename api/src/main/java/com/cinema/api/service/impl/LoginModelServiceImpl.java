package com.cinema.api.service.impl;

import com.cinema.api.security.auth.AuthUser;
import com.cinema.api.model.LoginModel;
import com.cinema.api.model.UserModel;
import com.cinema.api.service.LoginModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class LoginModelServiceImpl implements LoginModelService, UserDetailsService {

    @Value("${backend.server.url}")
    private String backend;

    @Autowired
    private BCryptPasswordEncoder encoder;

    private final String path = "/logins";

    @Override
    public LoginModel getLoginModelById(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backend + path + "/" + id, LoginModel.class);
    }

    @Override
    public List<LoginModel> getAllLoginModels() {
        RestTemplate restTemplate = new RestTemplate();
        LoginModel[] loginModels = restTemplate.getForObject(backend + path, LoginModel[].class);
        return loginModels != null ? Collections.emptyList() : Arrays.asList(loginModels);
    }

    @Override
    public LoginModel getLoginModelByEmail(String email) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backend + path + "/emails/" + email, LoginModel.class);
    }

    @Override
    public UserModel getUserModelByEmail(String email) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backend + path + "/users/" + email, UserModel.class);
    }

    @Override
    public LoginModel saveLoginModel(LoginModel login) {
        login.setPassword(encoder.encode(login.getPassword()));
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backend + path, login, LoginModel.class).getBody();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = getUserModelByEmail(username);
        LoginModel login = getLoginModelByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User with username: " + username + " not found");
        }
        return new AuthUser(login, user);
    }
}
