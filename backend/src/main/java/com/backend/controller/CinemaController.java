package com.backend.controller;

import com.backend.entity.Cinema;
import com.backend.pagination.PaginatorPage;
import com.backend.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cinemas")
public class CinemaController {

    @Autowired
    private CinemaService cinemaService;

    @GetMapping("")
    private List<Cinema> getAllCinemas() {
        return cinemaService.getAllCinemas();
    }

    @GetMapping(params = {"pageNumber", "pageSize"})
    private PaginatorPage<Cinema> getAllCinemas(@RequestParam int pageNumber, @RequestParam int pageSize) {
        return cinemaService.getAllCinemasByPage(pageNumber, pageSize);
    }

    @GetMapping("/{id}")
    private Cinema getCinemaById(@PathVariable Long id) {
        return cinemaService.getById(id);
    }

    @PostMapping("")
    private Cinema saveCinema(@Valid @RequestBody Cinema cinema) {
        return cinemaService.saveCinema(cinema);
    }

    @DeleteMapping("/{id}")
    private void deleteCinemaById(@PathVariable Long id) {
        cinemaService.deleteCinemaById(id);
    }

}
