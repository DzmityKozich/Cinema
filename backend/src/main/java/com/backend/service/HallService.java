package com.backend.service;

import com.backend.entity.Hall;

import java.util.List;

public interface HallService {
    Hall getHallById(Long id);
    List<Hall> getAllByCinema(Long id);
    Hall saveHall(Hall hall);
    void deleteHallById(Long id);
}
