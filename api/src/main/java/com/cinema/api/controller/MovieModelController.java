package com.cinema.api.controller;

import com.cinema.api.model.MovieModel;
import com.cinema.api.service.MovieModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieModelController {

    @Autowired
    private MovieModelService movieModelService;

    @GetMapping("")
    private List<MovieModel> getAllMovieModels(){
        return movieModelService.getAllMovieModels();
    }

    @GetMapping("/{id}")
    private MovieModel getMovieModelById(@PathVariable Long id){
        return movieModelService.getMovieModelById(id);
    }
}
