package com.cinema.api.security.jwt;

import com.cinema.api.model.RefreshTokenModel;

public class JwtRefreshToken {

    private String refreshToken;
    private String jwtToken;

    public JwtRefreshToken() {
    }

    public JwtRefreshToken(String refreshToken, String jwtToken) {
        this.refreshToken = refreshToken;
        this.jwtToken = jwtToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
