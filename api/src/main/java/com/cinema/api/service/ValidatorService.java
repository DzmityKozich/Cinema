package com.cinema.api.service;

import com.cinema.api.model.LoginModel;

import java.util.List;

public interface ValidatorService {
    boolean validator(LoginModel loginModel, List<LoginModel> loginModelList);
}
