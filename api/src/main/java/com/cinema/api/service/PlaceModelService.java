package com.cinema.api.service;

import com.cinema.api.model.PlaceModel;

import java.util.List;

public interface PlaceModelService {

    List<PlaceModel> getAllPlacesBySeance(Long id);
    List<PlaceModel> getAllPlaceModelsByBilling(Long id);
    PlaceModel savePlaceModel(PlaceModel place);
    void takePlace(PlaceModel[] places);
    void clearSelectedPlaceModels(PlaceModel[] places);
    PlaceModel selectPlace(PlaceModel placeModel);
}
