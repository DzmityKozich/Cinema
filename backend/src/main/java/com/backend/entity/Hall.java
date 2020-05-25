package com.backend.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "hall")
public class Hall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hall_id")
    private Long idHall;

    @Size(min = 1, max = 45)
    @Column(name = "hall_name")
    private String name;

    @Column(name = "hall_rows")
    private int rows;

    @Column(name = "hall_places_in_row")
    private int placesInRow;

    @ManyToOne
    @JoinColumn(name = "hall_id_cinema")
    private Cinema cinema;

    @OneToMany(mappedBy = "hall")
    private List<Seance> seances;

    public Hall() {
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

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hall hall = (Hall) o;
        return rows == hall.rows &&
                placesInRow == hall.placesInRow &&
                idHall.equals(hall.idHall) &&
                name.equals(hall.name) &&
                cinema.equals(hall.cinema);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idHall, name, rows, placesInRow, cinema);
    }

    @Override
    public String toString() {
        return "Hall{" +
                "idHall=" + idHall +
                ", name='" + name + '\'' +
                ", rows=" + rows +
                ", placesInRow=" + placesInRow +
                ", cinema=" + cinema +
                '}';
    }
}
