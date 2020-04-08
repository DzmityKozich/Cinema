package com.cinema.api.controller;

import com.cinema.api.model.MovieModel;
import com.cinema.api.service.MovieModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieModelController {

    @Autowired
    private MovieModelService movieModelService;

    @GetMapping(params = {"pageNumber", "pageSize"})
    private List<MovieModel> getAllMovieModels(@RequestParam int pageNumber, @RequestParam int pageSize){
        return movieModelService.getAllMovieModels(pageNumber, pageSize);
    }

    @GetMapping("/{id}")
    private MovieModel getMovieModelById(@PathVariable Long id){
        return movieModelService.getMovieModelById(id);
    }
}
