package com.cinema.api.model;

public class RefreshTokenModel {

    private Long Id;
    private String token;

    public RefreshTokenModel() {
    }

    public RefreshTokenModel(Long id, String token) {
        Id = id;
        this.token = token;
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
