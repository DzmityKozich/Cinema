package com.cinema.api.model;

public class RefreshTokenModel {

    private Long Id;
    private String token;
    private  UserModel user;

    public RefreshTokenModel() {
    }

    public RefreshTokenModel(Long id, String token, UserModel user) {
        Id = id;
        this.token = token;
        this.user = user;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
