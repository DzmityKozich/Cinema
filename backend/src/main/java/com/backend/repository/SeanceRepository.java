package com.backend.repository;

import com.backend.entity.Movie;
import com.backend.entity.Seance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface SeanceRepository extends JpaRepository<Seance, Long> {
    Seance findByIdSeance(Long id);
    List<Seance> findAllByMovie(Movie movie);
    List<Seance> findAllByDateAndMovie(LocalDate date, Movie movie);
    List<Seance> findAllByDateAndTime(LocalDate date, LocalTime time);
    @Query("SELECT s FROM Seance s JOIN s.hall h where h.cinema.idCinema = :id and s.date = :date")
    List<Seance> getAllSeancesByCinemaAndDate(@Param("id") Long id, @Param("date") LocalDate date);
}
