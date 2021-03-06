package com.backend.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Long idMovie;

    @Size(min = 1, max = 45)
    @Column(name = "movie_name")
    private String name;

    @Column(name = "movie_genre")
    private String genre;

    @Size(min = 1, max = 1000)
    @Column(name = "movie_description")
    private String description;

    @Column(name = "movie_poster")
    private String poster;

    @OneToMany(mappedBy = "movie")
    private List<Seance> seances;

    public Movie() {
    }

    public Movie(Long idMovie, String name, String genre, String description, String poster) {
        this.idMovie = idMovie;
        this.name = name;
        this.genre = genre;
        this.description = description;
        this.poster = poster;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public Long getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(Long idMovie) {
        this.idMovie = idMovie;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return idMovie.equals(movie.idMovie) &&
                name.equals(movie.name) &&
                genre.equals(movie.genre) &&
                description.equals(movie.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMovie, name, genre, description);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "idMovie=" + idMovie +
                ", name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
