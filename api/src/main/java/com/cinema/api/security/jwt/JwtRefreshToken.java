package com.cinema.api.security.jwt;

import com.cinema.api.model.UserModel;

import java.util.Objects;

public class JwtRefreshToken {

    private String refreshToken;
    private UserModel userModel;

    public JwtRefreshToken() {
    }

    public JwtRefreshToken(String refreshToken, UserModel userModel) {
        this.refreshToken = refreshToken;
        this.userModel = userModel;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JwtRefreshToken that = (JwtRefreshToken) o;
        return refreshToken.equals(that.refreshToken) &&
                userModel.equals(that.userModel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(refreshToken, userModel);
    }
}
