package com.backend.controller;

import com.backend.entity.Movie;
import com.backend.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping(params = {"pageNumber", "pageSize"})
    private List<Movie> getAllMovies(@RequestParam int pageNumber, @RequestParam int pageSize){
        Page<Movie> page = movieService.getAllMoviesByPage(pageNumber, pageSize);
        if(pageNumber > page.getTotalPages()){
            return null;
        }
        return page.getContent();
    }

    @GetMapping("/{id}")
    private Movie getMovieById(@PathVariable Long id){
        return movieService.getById(id);
    }

    @PostMapping("")
    private Movie saveMovie(@RequestBody Movie movie){
        return movieService.saveMovie(movie);
    }

    @DeleteMapping("/{id}")
    private void deleteMovieById(@PathVariable Long id){
        movieService.deleteMovieById(id);
    }
}
