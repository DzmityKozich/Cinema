package com.cinema.api.service;

import com.cinema.api.model.UserModel;

import java.util.List;

public interface UserModelService {
    UserModel getUserModelById(Long id);
    UserModel saveUserModel(UserModel userModel);
}
