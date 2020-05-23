package com.backend.service;

import com.backend.entity.Movie;
import com.backend.pagination.PaginatorPage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MovieService {
    Movie getById(Long id);
    Movie saveMovie(Movie movie);
    PaginatorPage<Movie> getAllMoviesByPage(int pageNumber, int pageSize);
    PaginatorPage<Movie> getAllMoviesByGenre(String genre, int pageNumber, int pageSize);
    List<Movie> getAllMovies();
    void deleteMovieById(Long id);
}
