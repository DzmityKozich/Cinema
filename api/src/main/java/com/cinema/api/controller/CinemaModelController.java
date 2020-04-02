package com.cinema.api.controller;

import com.cinema.api.model.CinemaModel;
import com.cinema.api.service.CinemaModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cinemas")
public class CinemaModelController {

    @Autowired
    private CinemaModelService cinemaModelService;

    @GetMapping("")
    private List<CinemaModel> getAllCinemaModels(){
        return cinemaModelService.getAllCinemaModels();
    }

    @GetMapping("/{id}")
    private CinemaModel getCinemaModelById(@PathVariable Long id){
        return cinemaModelService.getCinemaById(id);
    }

    @PostMapping("")
    private CinemaModel saveCinemaModel(@RequestBody CinemaModel cinemaModel){
        return cinemaModelService.saveCinema(cinemaModel);
    }
}
