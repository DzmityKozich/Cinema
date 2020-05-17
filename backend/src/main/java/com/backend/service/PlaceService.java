package com.backend.service;

import com.backend.entity.Place;

import java.util.List;

public interface PlaceService {
    Place getPlaceById(Long id);
    List<Place> getAllPlaces();
    Place savePlace(Place place);
    void clearSelectedPlaces(List<Place> places);
    List<Place> getAllBySeance(Long id);
    List<Place> getAllPlacesByBilling(Long id);
    void takePlace(List<Place> places);
    void deletePlaceById(Long id);
}
