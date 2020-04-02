package com.cinema.api.service;

import com.cinema.api.model.CinemaModel;

import java.util.List;

public interface CinemaModelService {
    CinemaModel getCinemaById(Long id);
    List<CinemaModel> getAllCinemaModels();
    CinemaModel saveCinema(CinemaModel cinemaModel);
}
