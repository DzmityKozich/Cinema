package com.backend.service;

import com.backend.entity.Place;

import java.util.List;

public interface PlaceService {
    Place getPlaceById(Long id);
    List<Place> getAllPlaces();
    Place savePlace(Place place);
    List<Place> getAllBySeance(Long id);
    void deletePlaceById(Long id);
}
