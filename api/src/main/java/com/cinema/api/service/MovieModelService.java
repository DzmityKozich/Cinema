package com.cinema.api.service;

import com.cinema.api.model.MovieModel;
import com.cinema.api.pagination.PaginatorPageModel;

import java.util.List;

public interface MovieModelService {
    MovieModel getMovieModelById(Long id);
    PaginatorPageModel<MovieModel> getAllMovieModels(int pageNumber, int pageSize);
}
