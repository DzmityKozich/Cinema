package com.backend.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "usr")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usr_id")
    private Long idUser;

    @Column(name  = "usr_Fname")
    private String firstName;

    @Column(name = "usr_Lname")
    private String lastName;

    @Column(name = "usr_role")
    private String role;

    @OneToOne(mappedBy = "loginUser")
    private Login login;

    @OneToMany(mappedBy = "billingUser")
    private List<Billing> billings;

    public User() {
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
        User user = (User) o;
        return idUser.equals(user.idUser) &&
                firstName.equals(user.firstName) &&
                lastName.equals(user.lastName) &&
                role.equals(user.role) &&
                login.equals(user.login) &&
                billings.equals(user.billings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, firstName, lastName, role, login, billings);
    }

    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role='" + role + '\'' +
                ", login=" + login +
                ", billings=" + billings +
                '}';
    }
}
