package com.cinema.api.service;

import com.cinema.api.model.CinemaModel;
import com.cinema.api.model.SeanceModel;

import java.util.List;

public interface SeanceModelService {

    List<SeanceModel> getSeanceModelsByMovie(Long id);
//    List<CinemaModel> getAllCinemaModelsByMovie(Long id);
}
