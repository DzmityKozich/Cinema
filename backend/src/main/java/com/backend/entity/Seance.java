package com.backend.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "seance")
public class Seance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seance_id")
    private Long idSeance;

    @Column(name = "seance_date")
    private LocalDate date;

    @Column(name = "seance_time")
    private LocalTime time;

    @Column(name = "seance_price")
    private double price;

    @OneToMany(mappedBy = "seance")
    private List<Place> places;

    @ManyToOne
    @JoinColumn(name = "seance_id_movie")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "seance_id_hall")
    private Hall hall;

    public Seance() {
    }

    public Long getIdSeance() {
        return idSeance;
    }

    public void setIdSeance(Long idSeance) {
        this.idSeance = idSeance;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seance seance = (Seance) o;
        return Double.compare(seance.price, price) == 0 &&
                idSeance.equals(seance.idSeance) &&
                time.equals(seance.time) &&
                places.equals(seance.places) &&
                movie.equals(seance.movie) &&
                hall.equals(seance.hall);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSeance, time, price, places, movie, hall);
    }

    @Override
    public String toString() {
        return "Seance{" +
                "idSeance=" + idSeance +
                ", time=" + time +
                ", price=" + price +
                ", movie=" + movie +
                ", hall=" + hall +
                '}';
    }
}
