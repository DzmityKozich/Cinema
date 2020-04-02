package com.backend.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "billing")
public class Billing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "billing_id")
    private Long idBilling;

    @Column(name = "billing_balance")
    private double balance;

    @ManyToOne
    @JoinColumn(name = "billing_id_usr")
    private User billingUser;

    @OneToMany(mappedBy = "billing")
    private List<Place> places;

    public Billing() {
    }

    public Long getIdBilling() {
        return idBilling;
    }

    public void setIdBilling(Long idBilling) {
        this.idBilling = idBilling;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public User getBillingUser() {
        return billingUser;
    }

    public void setBillingUser(User billingUser) {
        this.billingUser = billingUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Billing billing = (Billing) o;
        return Double.compare(billing.balance, balance) == 0 &&
                idBilling.equals(billing.idBilling) &&
                billingUser.equals(billing.billingUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBilling, balance, billingUser);
    }

    @Override
    public String toString() {
        return "Billing{" +
                "idBilling=" + idBilling +
                ", balance=" + balance +
                ", billingUser=" + billingUser +
                '}';
    }
}
