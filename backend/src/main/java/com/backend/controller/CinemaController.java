package com.backend.controller;

import com.backend.entity.Cinema;
import com.backend.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cinemas")
public class CinemaController {

    @Autowired
    private CinemaService cinemaService;

    @GetMapping("")
    private List<Cinema> getAllCinemas(){
        return cinemaService.getAllCinemas();
    }

    @GetMapping("/{id}")
    private Cinema getCinemaById(@PathVariable Long id){
        return cinemaService.getById(id);
    }

    @PostMapping("")
    private Cinema saveCinema(@RequestBody Cinema cinema){
        return cinemaService.saveCinema(cinema);
    }

    @DeleteMapping("/{id}")
    private void deleteCinemaById(@PathVariable Long id){
        cinemaService.deleteCinemaById(id);
    }

}
