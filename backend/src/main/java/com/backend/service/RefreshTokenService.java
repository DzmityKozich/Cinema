package com.backend.service;

import com.backend.entity.RefreshToken;

public interface RefreshTokenService {

    RefreshToken generateRefreshToken();
    RefreshToken getRefreshTokenByToken(String token);
    void deleteRefreshTokenByToken(String token);
}
