package com.cinema.api.service;

import com.cinema.api.model.LoginModel;
import com.cinema.api.model.UserModel;

public interface LoginModelService {

    LoginModel getLoginModelById(Long id);
    LoginModel saveLoginModel(LoginModel login);
    UserModel getUserModelByLoginModel(String email);
}
