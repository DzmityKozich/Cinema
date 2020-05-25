package com.cinema.api.model;

import java.util.Objects;

public class SeanceModel {

    private Long idSeance;
    private String date;
    private String time;
    private double price;
    private MovieModel movie;
    private HallModel hall;

    public SeanceModel() {
    }

    public SeanceModel(Long idSeance, String date, String time, double price, MovieModel movie, HallModel hall) {
        this.idSeance = idSeance;
        this.date = date;
        this.time = time;
        this.price = price;
        this.movie = movie;
        this.hall = hall;
    }

    public Long getIdSeance() {
        return idSeance;
    }

    public void setIdSeance(Long idSeance) {
        this.idSeance = idSeance;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public MovieModel getMovie() {
        return movie;
    }

    public void setMovie(MovieModel movie) {
        this.movie = movie;
    }

    public HallModel getHall() {
        return hall;
    }

    public void setHall(HallModel hall) {
        this.hall = hall;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SeanceModel that = (SeanceModel) o;
        return Double.compare(that.price, price) == 0 &&
                idSeance.equals(that.idSeance) &&
                date.equals(that.date) &&
                time.equals(that.time) &&
                movie.equals(that.movie) &&
                hall.equals(that.hall);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSeance, date, time, price, movie, hall);
    }

    @Override
    public String toString() {
        return "SeanceModel{" +
                "idSeance=" + idSeance +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", price=" + price +
                ", movie=" + movie +
                ", hall=" + hall +
                '}';
    }
}
