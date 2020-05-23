package com.cinema.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CinemaModel {

    private Long idCinema;
    private String name;
    private String address;
    private String img;

    public CinemaModel(){

    }

    public CinemaModel(Long idCinema, String name, String address, String img) {
        this.idCinema = idCinema;
        this.name = name;
        this.address = address;
        this.img = img;
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
}
