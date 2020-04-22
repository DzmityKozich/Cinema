package com.cinema.api.service;

import com.cinema.api.model.PlaceModel;

import java.util.List;

public interface PlaceModelService {

    List<PlaceModel> getAllPlacesBySeance(Long id);
}
