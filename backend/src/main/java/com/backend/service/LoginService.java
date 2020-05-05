package com.backend.service;

import com.backend.entity.Login;
import com.backend.entity.User;

public interface LoginService {
    Login getById(Long id);
    Login saveLogin(Login login);
    Login getLoginByEmail(String email);
    User getUserByEmail(String email);
    void deleteLoginById(Long id);
}
