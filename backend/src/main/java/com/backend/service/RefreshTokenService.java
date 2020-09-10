package com.backend.service;

import com.backend.entity.RefreshToken;
import com.backend.entity.User;

public interface RefreshTokenService {

    RefreshToken generateRefreshToken(User user);
    RefreshToken getByUser(User user);
    RefreshToken getRefreshTokenByToken(String token);
    void deleteRefreshTokenByToken(String token);
}
