package com.backend.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Objects;

@Entity
@Table(name = "login")
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "login_id")
    private Long idLogin;

    @Email
    @Column(name = "login_email")
    private String email;

    @Column(name = "login_password")
    private String password;

    @OneToOne
    @JoinColumn(name = "login_id_usr")
    private User loginUser;

    public Login() {
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

    public User getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(User loginUser) {
        this.loginUser = loginUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Login login = (Login) o;
        return idLogin.equals(login.idLogin) &&
                email.equals(login.email) &&
                password.equals(login.password) &&
                loginUser.equals(login.loginUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idLogin, email, password, loginUser);
    }

    @Override
    public String toString() {
        return "Login{" +
                "idLogin=" + idLogin +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", loginUser=" + loginUser +
                '}';
    }
}
