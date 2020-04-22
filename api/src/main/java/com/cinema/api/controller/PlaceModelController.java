package com.cinema.api.controller;

import com.cinema.api.model.PlaceModel;
import com.cinema.api.service.PlaceModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
