package com.backend.service.impl;

import com.backend.entity.Cinema;
import com.backend.repository.CinemaRepository;
import com.backend.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CinemaServiceImpl implements CinemaService {

    @Autowired
    private CinemaRepository cinemaRepository;

    @Override
    public Cinema getById(Long id) {
        return cinemaRepository.findByIdCinema(id);
    }

    @Override
    public List<Cinema> getAllCinemas() {
        return cinemaRepository.findAll();
    }

    @Override
    public Cinema saveCinema(Cinema cinema) {
        return cinemaRepository.save(cinema);
    }

    @Override
    public void deleteCinemaById(Long id) {
        cinemaRepository.deleteById(id);
    }
}

