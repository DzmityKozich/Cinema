package com.cinema.api.model;

public class HallModel {

    private Long idHall;
    private String name;
    private int rows;
    private int placesInRow;
    private CinemaModel cinema;

    public HallModel() {
    }

    public HallModel(Long idHall, String name, int rows, int placesInRow, CinemaModel cinema) {
        this.idHall = idHall;
        this.name = name;
        this.rows = rows;
        this.placesInRow = placesInRow;
        this.cinema = cinema;
    }

    public Long getIdHall() {
        return idHall;
    }

    public void setIdHall(Long idHall) {
        this.idHall = idHall;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getPlacesInRow() {
        return placesInRow;
    }

    public void setPlacesInRow(int placesInRow) {
        this.placesInRow = placesInRow;
    }

    public CinemaModel getCinema() {
        return cinema;
    }

    public void setCinema(CinemaModel cinema) {
        this.cinema = cinema;
    }
}
