package com.backend.controller;

import com.backend.entity.Movie;
import com.backend.pagination.PaginatorPage;
import com.backend.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("")
    private List<Movie> getAllMovies(){
        return movieService.getAllMovies();
    }

    @GetMapping(params = {"pageNumber", "pageSize"})
    private PaginatorPage<Movie> getAllMovies(@RequestParam int pageNumber, @RequestParam int pageSize){
        return movieService.getAllMoviesByPage(pageNumber, pageSize);
    }

    @GetMapping(params = {"genre", "pageNumber", "pageSize"})
    private PaginatorPage<Movie> getAllMoviesByGenre(@RequestParam String genre, @RequestParam int pageNumber, @RequestParam int pageSize) {
        return movieService.getAllMoviesByGenre(genre, pageNumber, pageSize);
    }

    @GetMapping("/{id}")
    private Movie getMovieById(@PathVariable Long id){
        return movieService.getById(id);
    }

    @PostMapping("")
    private Movie saveMovie(@Valid @RequestBody Movie movie){
        return movieService.saveMovie(movie);
    }

    @DeleteMapping("/{id}")
    private void deleteMovieById(@PathVariable Long id){
        movieService.deleteMovieById(id);
    }
}
