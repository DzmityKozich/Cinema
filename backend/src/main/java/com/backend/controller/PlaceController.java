package com.backend.controller;

import com.backend.entity.Place;
import com.backend.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/places")
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    @GetMapping("")
    private List<Place> getAllPlaces(){
        return placeService.getAllPlaces();
    }

    @GetMapping("/{id}")
    private Place getPlaceById(@PathVariable Long id){
        return placeService.getPlaceById(id);
    }

    @GetMapping("/seances/{id}")
    private List<Place> getAllBySeance(@PathVariable Long id){
        return placeService.getAllBySeance(id);
    }

    @PostMapping("")
    private Place savePlace(@RequestBody Place place){
        return placeService.savePlace(place);
    }

    @PostMapping("/reservation")
    private void takePlace(@RequestBody Place[] places){
        placeService.takePlace(Arrays.asList(places));
    }

    @DeleteMapping("/{id}")
    private void deletePlaceById(@PathVariable Long id){
        placeService.deletePlaceById(id);
    }
}
