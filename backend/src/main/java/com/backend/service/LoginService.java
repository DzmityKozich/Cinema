package com.backend.service;

import com.backend.entity.Login;
import com.backend.entity.User;

import java.util.List;

public interface LoginService {
    Login getById(Long id);
    List<Login> getAllLogins();
    Login saveLogin(Login login);
    Login getLoginByEmail(String email);
    User getUserByEmail(String email);
    String getEmailByUser(User user);
    void deleteLoginById(Long id);
}
