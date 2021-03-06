package com.cinema.api.service;

import com.cinema.api.model.CinemaModel;
import com.cinema.api.model.SeanceModel;

import java.util.List;

public interface SeanceModelService {

    List<SeanceModel> getSeanceModelsByMovie(Long id);
    List<SeanceModel> getAllSeanceModelsByDateAndMovie(String date, Long movieId);
    SeanceModel saveSeanceModel(SeanceModel seance);
    List<SeanceModel> getAllSeanceModelsByCinemasAndDate(Long id, String date);
}
