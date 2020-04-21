package com.cinema.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieModel {

    private Long idMovie;
    private String name;
    private String genre;
    private String description;
    private String poster;

    public MovieModel(){ }

    public MovieModel(Long idMovie, String name, String genre, String description, String poster) {
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
        MovieModel that = (MovieModel) o;
        return idMovie.equals(that.idMovie) &&
                name.equals(that.name) &&
                genre.equals(that.genre) &&
                description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMovie, name, genre, description);
    }

    @Override
    public String toString() {
        return "MovieModel{" +
                "idMovie=" + idMovie +
                ", name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
