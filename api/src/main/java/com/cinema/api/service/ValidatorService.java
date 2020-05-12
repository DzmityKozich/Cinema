package com.cinema.api.service;

import com.cinema.api.model.LoginModel;

import java.util.List;

public interface ValidatorService {
    boolean validator(String login, List<LoginModel> loginModelList);
}
