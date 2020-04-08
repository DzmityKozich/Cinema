package com.backend.service;

import com.backend.entity.Movie;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MovieService {
    Movie getById(Long id);
    Movie saveMovie(Movie movie);
    Page<Movie> getAllMovies(int pageNumber, int pageSize);
    void deleteMovieById(Long id);
}
