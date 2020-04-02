package com.backend.service;

import com.backend.entity.Seance;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface SeanceService {
    Seance getSeanceById(Long id);
    List<Seance> getAllSeances();
    List<Seance> getSeancesByMovie(Long id);
    List<Seance> getAllSeancesByDateAndTime(LocalDate date, LocalTime time);
    Seance saveSeance(Seance seance);
    void deleteSeanceById(Long id);
}
