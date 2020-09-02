package com.cinema.api.service;

import com.cinema.api.model.RefreshTokenModel;

public interface RefreshTokenModelService {

    RefreshTokenModel getRefreshTokenByToken(String token);
    RefreshTokenModel generateRefreshToken();
    void deleteRefreshTokenByToken(String token);
}
