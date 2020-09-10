package com.backend.service.impl;

import com.backend.entity.RefreshToken;
import com.backend.entity.User;
import com.backend.repository.RefreshTokenRepository;
import com.backend.service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Override
    public RefreshToken generateRefreshToken(User user) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setUser(user);
        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public RefreshToken getByUser(User user) {
        return refreshTokenRepository.findByUser(user);
    }

    @Override
    public RefreshToken getRefreshTokenByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    @Transactional
    @Override
    public void deleteRefreshTokenByToken(String token) {
        refreshTokenRepository.deleteByToken(token);
    }
}
