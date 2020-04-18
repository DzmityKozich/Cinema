package com.cinema.api.controller;

import com.cinema.api.model.CinemaModel;
import com.cinema.api.model.SeanceModel;
import com.cinema.api.service.SeanceModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/seances")
public class SeanceModelController {

    @Autowired
    private SeanceModelService seanceModelService;

    @GetMapping("/movies/{id}")
    private List<SeanceModel> getSeanceModelsByMovie(@PathVariable Long id){
        return seanceModelService.getSeanceModelsByMovie(id);
    }

//    @GetMapping("/m/{id}")
//    private List<CinemaModel> getAllCinemaModelsByMovie(@PathVariable Long id){
//        return seanceModelService.getAllCinemaModelsByMovie(id);
//    }
}
