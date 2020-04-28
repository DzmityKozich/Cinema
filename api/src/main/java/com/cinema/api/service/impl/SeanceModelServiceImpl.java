package com.cinema.api.service.impl;

import com.cinema.api.model.SeanceModel;
import com.cinema.api.service.SeanceModelService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class SeanceModelServiceImpl implements SeanceModelService {

    @Value("${backend.server.url}")
    private String backend;

    private final String path = "/seances";

    @Override
    public List<SeanceModel> getSeanceModelsByMovie(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        SeanceModel[] seanceModels = restTemplate.getForObject(backend + path + "/movies/" + id, SeanceModel[].class);
        return seanceModels == null ? Collections.emptyList() : Arrays.asList(seanceModels);
    }

    @Override
    public List<SeanceModel> getAllSeanceModelsByDateAndMovie(String date, Long movieId) {
        RestTemplate restTemplate = new RestTemplate();
        SeanceModel[] seanceModels = restTemplate.getForObject(backend + path + "/date/" + date + "/movies/" + movieId, SeanceModel[].class);
        return seanceModels == null ? Collections.emptyList() : Arrays.asList(seanceModels);
    }
}
