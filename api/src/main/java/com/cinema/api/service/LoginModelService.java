package com.cinema.api.service;

import com.cinema.api.model.LoginModel;
import com.cinema.api.model.UserModel;

import java.util.List;

public interface LoginModelService {
    String getEmailByUser(UserModel userModel);
    LoginModel getLoginModelById(Long id);
    List<LoginModel> getAllLoginModels();
    LoginModel getLoginModelByEmail(String email);
    LoginModel saveLoginModel(LoginModel login);
    UserModel getUserModelByEmail(String email);
}
