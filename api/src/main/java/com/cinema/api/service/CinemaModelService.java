package com.cinema.api.service;

import com.cinema.api.model.CinemaModel;
import com.cinema.api.pagination.PaginatorPageModel;

import java.util.List;

public interface CinemaModelService {
    CinemaModel getCinemaById(Long id);
    PaginatorPageModel<CinemaModel> getAllCinemaModels(int pageNumber, int pageSize);
    CinemaModel saveCinema(CinemaModel cinemaModel);
}
