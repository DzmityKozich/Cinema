package com.backend.controller;

import com.backend.entity.Seance;
import com.backend.service.SeanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/seances")
public class SeanceController {

    @Autowired
    private SeanceService seanceService;

    @GetMapping("")
    private List<Seance> getAllSeances(){
        return seanceService.getAllSeances();
    }

    @GetMapping("/{id}")
    private Seance getSeanceBuId(@PathVariable Long id){
        return seanceService.getSeanceById(id);
    }

    @GetMapping("/date/{date}/time/{time}")
    private List<Seance> getSeancesByTimeAndDate(@PathVariable String time, @PathVariable String date){
        return seanceService.getAllSeancesByDateAndTime(LocalDate.parse(date), LocalTime.parse(time));
    }

    @GetMapping("/cinemas/{id}/date/{date}")
    private List<Seance> getAllSeancesByCinema(@PathVariable Long id, @PathVariable String date){
        return seanceService.getAllSeancesByCinemaAndDate(id, LocalDate.parse(date));
    }

    @GetMapping("/movies/{id}")
    private List<Seance> getSeancesByMovie(@PathVariable Long id){
        return seanceService.getSeancesByMovie(id);
    }

    @GetMapping("/date/{date}/movies/{movieId}")
    private List<Seance> getAllSeancesByDateAndMovie(@PathVariable String date, @PathVariable Long movieId){
        return seanceService.getAllSeancesByDateAndMovie(LocalDate.parse(date), movieId);
    }

    @PostMapping("")
    private Seance saveSeance(@RequestBody Seance seance){
        return seanceService.saveSeance(seance);
    }

    @DeleteMapping("/{id}")
    private void deleteSeanceById(@PathVariable Long id){
        seanceService.deleteSeanceById(id);
    }

}
