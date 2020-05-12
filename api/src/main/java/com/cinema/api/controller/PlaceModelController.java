package com.cinema.api.controller;

import com.cinema.api.model.PlaceModel;
import com.cinema.api.service.PlaceModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/places")
public class PlaceModelController {

    @Autowired
    private PlaceModelService placeModelService;

    @GetMapping("/seances/{id}")
    private List<PlaceModel> getAllPlacesBySeance(@PathVariable Long id){
        return placeModelService.getAllPlacesBySeance(id);
    }

    @PostMapping("")
    private PlaceModel savePlaceModel(@RequestBody PlaceModel placeModel){
        return placeModelService.savePlaceModel(placeModel);
    }

    @PostMapping("/reservation")
    private void takePlace(@RequestBody PlaceModel[] places){
        placeModelService.takePlace(places);
    }

    @PostMapping("/cleaning")
    private void clearSelectedPlaceModels(@RequestBody PlaceModel[] placeModels){
        placeModelService.clearSelectedPlaceModels(placeModels);
    }

    @PostMapping("/selection")
    private PlaceModel selectPlace(@RequestBody PlaceModel place){
        return placeModelService.selectPlace(place);
    }
}
