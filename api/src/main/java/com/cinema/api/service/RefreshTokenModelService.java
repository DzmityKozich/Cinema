package com.cinema.api.service;

import com.cinema.api.model.RefreshTokenModel;
import com.cinema.api.model.UserModel;

public interface RefreshTokenModelService {

    RefreshTokenModel getRefreshTokenByToken(String token);
    RefreshTokenModel getRefreshTokenByUserModel(UserModel userModel);
    RefreshTokenModel generateRefreshToken(UserModel userModel);
    void deleteRefreshTokenByToken(String token);
}
