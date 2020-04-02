package com.backend.service;

import com.backend.entity.Movie;

import java.nio.file.LinkOption;
import java.util.List;

public interface MovieService {
    Movie getById(Long id);
    Movie saveMovie(Movie movie);
    List<Movie> getAllMovies();
    void deleteMovieById(Long id);
}
