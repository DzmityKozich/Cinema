package com.cinema.api.service.impl;

import com.cinema.api.model.PlaceModel;
import com.cinema.api.service.PlaceModelService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class PlaceModelServiceImpl implements PlaceModelService {

    @Value("${backend.server.url}")
    private String backend;

    private final String path = "/places";

    @Override
    public List<PlaceModel> getAllPlacesBySeance(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        PlaceModel[] placeModels = restTemplate.getForObject(backend + path + "/seances/" + id, PlaceModel[].class);
        return placeModels == null? Collections.emptyList() : Arrays.asList(placeModels);
    }

    @Override
    public PlaceModel savePlaceModel(PlaceModel place) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backend + path, place, PlaceModel.class).getBody();
    }

    @Override
    public void takePlace(PlaceModel[] places) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForEntity(backend + path  + "/reservation", places, PlaceModel.class).getBody();
    }
}
