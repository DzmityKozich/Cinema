package com.cinema.api.controller;

import com.cinema.api.model.MovieModel;
import com.cinema.api.pagination.PaginatorPageModel;
import com.cinema.api.service.MovieModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

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

    @GetMapping(params = {"pageNumber", "pageSize"})
    private PaginatorPageModel<MovieModel> getAllMovieModels(@RequestParam int pageNumber, @RequestParam int pageSize){
        return movieModelService.getAllMovieModelsByPage(pageNumber, pageSize);
    }

    @GetMapping("/{id}")
    private MovieModel getMovieModelById(@PathVariable Long id){
        return movieModelService.getMovieModelById(id);
    }

    @GetMapping(params = {"genre", "pageNumber", "pageSize"})
    private PaginatorPageModel<MovieModel> getAllMovieModelsByGenre(@RequestParam String genre, @RequestParam int pageNumber, @RequestParam int pageSize) {
        return movieModelService.getAllMovieModelsByGenres(genre, pageNumber, pageSize);
    }

    @PostMapping("")
    private MovieModel saveMovieModel(@RequestBody MovieModel movie){
        return movieModelService.saveMovieModel(movie);
    }
}
