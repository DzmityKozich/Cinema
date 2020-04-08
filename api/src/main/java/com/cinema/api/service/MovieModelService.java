package com.cinema.api.service;

import com.cinema.api.model.MovieModel;

import java.util.List;

public interface MovieModelService {
    MovieModel getMovieModelById(Long id);
    List<MovieModel> getAllMovieModels(int pageNumber, int pageSize);
}
