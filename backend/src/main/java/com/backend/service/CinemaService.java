package com.backend.service;

import com.backend.entity.Cinema;

import java.util.List;

public interface CinemaService {
    Cinema getById(Long id);
    List<Cinema> getAllCinemas();
    Cinema saveCinema(Cinema cinema);
    void deleteCinemaById(Long id);
}
