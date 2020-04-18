package com.backend.service.impl;

import com.backend.entity.Cinema;
import com.backend.entity.Place;
import com.backend.entity.Seance;
import com.backend.repository.MovieRepository;
import com.backend.repository.PlaceRepository;
import com.backend.repository.SeanceRepository;
import com.backend.service.SeanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class SeanceServiceImpl implements SeanceService {

    @Autowired
    private SeanceRepository seanceRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @Override
    public Seance getSeanceById(Long id) {
        return seanceRepository.findByIdSeance(id);
    }

    @Override
    public List<Seance> getAllSeances() {
        return seanceRepository.findAll();
    }

    @Override
    public Seance saveSeance(Seance seance) {
//        List<Place> places = new ArrayList<>();
//        for (int i = 1; i <= seance.getHall().getRows(); i++){
//            for (int j = 1; j <= seance.getHall().getPlacesInRow(); j++){
//                places.add(new Place())
//            }
//        }
        return seanceRepository.save(seance);
    }

    @Override
    public List<Seance> getAllSeancesByDateAndTime(LocalDate date, LocalTime time) {
        return seanceRepository.findAllByDateAndTime(date, time);
    }

    @Override
    public List<Seance> getSeancesByMovie(Long id) {
        List<Seance> seances = new ArrayList<>();
        for (Seance s: seanceRepository.findAllByMovie(movieRepository.findByIdMovie(id))) {
            if ((s.getDate().compareTo(LocalDate.now()) == 0 && s.getTime().isAfter(LocalTime.now())) ||
                    s.getDate().compareTo(LocalDate.now()) > 0){
                seances.add(s);
            }
        }
        return seances;
    }

//    @Override
//    public List<Cinema> getAllCinemasByMovie(Long id){
//        List<Cinema> cinemas = new ArrayList<>();
//        for (Seance s : getSeancesByMovie(id)
//             ) {
//            cinemas.add(s.getHall().getCinema());
//        }
//        return cinemas;
//    }

    @Override
    public void deleteSeanceById(Long id) {
        seanceRepository.deleteById(id);
    }
}
