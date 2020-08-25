package com.backend.service;

import com.backend.entity.Hall;

import java.util.List;

public interface HallService {
    Hall getHallById(Long id);
    List<Hall> getAllHalls();
    List<Hall> getAllByCinema(Long id);
    Hall saveHall(Hall hall);
    List<Hall> saveHalls(List<Hall> halls);
    void deleteHallById(Long id);
}
