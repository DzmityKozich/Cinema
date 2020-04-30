package com.backend.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "place")
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "place_id")
    private Long idPlace;

    @Column(name = "place_row")
    private int row;

    @Column(name = "place_site")
    private int site;

    @Column(name = "place_state")
    private String state;

    @ManyToOne
    @JoinColumn(name = "place_id_seance")
    private Seance seance;

    @ManyToOne
    @JoinColumn(name = "place_id_billing")
    private Billing billing;

    public Place() {
    }

    public Place(int row, int site, String state, Seance seance) {
        this.row = row;
        this.site = site;
        this.state = state;
        this.seance = seance;
    }

    public Long getIdPlace() {
        return idPlace;
    }

    public void setIdPlace(Long idPlace) {
        this.idPlace = idPlace;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getSite() {
        return site;
    }

    public void setSite(int site) {
        this.site = site;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Seance getSeance() {
        return seance;
    }

    public void setSeance(Seance seance) {
        this.seance = seance;
    }

    public Billing getBilling() {
        return billing;
    }

    public void setBilling(Billing billing) {
        this.billing = billing;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Place place = (Place) o;
        return row == place.row &&
                site == place.site &&
                idPlace.equals(place.idPlace) &&
                state.equals(place.state) &&
                seance.equals(place.seance) &&
                billing.equals(place.billing);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPlace, row, site, state, seance, billing);
    }

    @Override
    public String toString() {
        return "Place{" +
                "idPlace=" + idPlace +
                ", row=" + row +
                ", site=" + site +
                ", state='" + state + '\'' +
                ", seance=" + seance +
                ", billing=" + billing +
                '}';
    }
}
