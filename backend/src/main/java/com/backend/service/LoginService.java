package com.backend.service;

import com.backend.entity.Login;
import com.backend.entity.User;

public interface LoginService {
    Login getById(Long id);
    Login saveLogin(Login login);
    User getUserByLogin(String email);
    void deleteLoginById(Long id);
}
