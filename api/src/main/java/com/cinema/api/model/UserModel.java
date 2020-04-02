package com.cinema.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserModel {

    private Long idUser;
    private String firstName;
    private String lastName;
    private String role;

    public UserModel(){ }

    public UserModel(Long idUser, String firstName, String lastName, String role) {
        this.idUser = idUser;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserModel userModel = (UserModel) o;
        return idUser.equals(userModel.idUser) &&
                firstName.equals(userModel.firstName) &&
                lastName.equals(userModel.lastName) &&
                role.equals(userModel.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, firstName, lastName, role);
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "idUser=" + idUser +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
