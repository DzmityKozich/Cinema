package com.backend.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "cinema")
public class Cinema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cinema_id")
    private Long idCinema;

    @Size(min = 1, max = 45)
    @Column(name = "cinema_name")
    private String name;

    @Size(min = 1, max = 45)
    @Column(name = "cinema_address")
    protected String address;

    @Column(name = "cinema_img")
    private String img;

    @OneToMany(mappedBy = "cinema")
    private List<Hall> halls;

    public Cinema() {
    }

    public Long getIdCinema() {
        return idCinema;
    }

    public void setIdCinema(Long idCinema) {
        this.idCinema = idCinema;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cinema cinema = (Cinema) o;
        return idCinema.equals(cinema.idCinema) &&
                name.equals(cinema.name) &&
                address.equals(cinema.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCinema, name, address);
    }

    @Override
    public String toString() {
        return "Cinema{" +
                "idCinema=" + idCinema +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
