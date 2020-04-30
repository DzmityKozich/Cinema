package com.cinema.api.model;

public class LoginModel {

    private Long idLogin;
    private String email;
    private String password;
    private UserModel loginUser;

    public LoginModel() {
    }

    public LoginModel(Long idLogin, String email, String password, UserModel loginUser) {
        this.idLogin = idLogin;
        this.email = email;
        this.password = password;
        this.loginUser = loginUser;
    }

    public Long getIdLogin() {
        return idLogin;
    }

    public void setIdLogin(Long idLogin) {
        this.idLogin = idLogin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserModel getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(UserModel loginUser) {
        this.loginUser = loginUser;
    }
}
