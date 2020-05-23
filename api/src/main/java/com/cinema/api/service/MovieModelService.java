package com.cinema.api.service;

import com.cinema.api.model.MovieModel;
import com.cinema.api.pagination.PaginatorPageModel;

import java.util.List;

public interface MovieModelService {
    MovieModel getMovieModelById(Long id);
    List<MovieModel> getAllMovieModels();
    PaginatorPageModel<MovieModel> getAllMovieModelsByPage(int pageNumber, int pageSize);
    PaginatorPageModel<MovieModel> getAllMovieModelsByGenres(String genre, int pageNumber, int pageSize);
    MovieModel saveMovieModel(MovieModel movie);
}
