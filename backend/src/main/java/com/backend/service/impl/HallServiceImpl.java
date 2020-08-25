package com.backend.service.impl;

import com.backend.entity.Hall;
import com.backend.repository.CinemaRepository;
import com.backend.repository.HallRepository;
import com.backend.service.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HallServiceImpl implements HallService {

    @Autowired
    private HallRepository hallRepository;

    @Autowired
    private CinemaRepository cinemaRepository;

    @Override
    public List<Hall> getAllHalls() {
        return hallRepository.findAll();
    }

    @Override
    public Hall getHallById(Long id) {
        return hallRepository.findByIdHall(id);
    }

    @Override
    public List<Hall> getAllByCinema(Long id) {
        return hallRepository.findAllByCinema(cinemaRepository.findByIdCinema(id));
    }

    @Override
    public Hall saveHall(Hall hall) {
        return hallRepository.save(hall);
    }

    @Override
    public List<Hall> saveHalls(List<Hall> halls) {
        return hallRepository.saveAll(halls);
    }

    @Override
    public void deleteHallById(Long id) {
        hallRepository.deleteById(id);
    }
}
