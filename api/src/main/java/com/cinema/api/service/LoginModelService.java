package com.cinema.api.service;

import com.cinema.api.model.LoginModel;
import com.cinema.api.model.UserModel;

public interface LoginModelService {

    LoginModel getLoginModelById(Long id);
    LoginModel getLoginModelByEmail(String email);
    LoginModel saveLoginModel(LoginModel login);
    UserModel getUserModelByEmail(String email);
}
