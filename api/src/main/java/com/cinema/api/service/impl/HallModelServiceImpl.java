package com.cinema.api.service.impl;

import com.cinema.api.model.HallModel;
import com.cinema.api.service.HallModelService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class HallModelServiceImpl implements HallModelService {

    @Value("${backend.server.url}")
    private String backend;

    private final String path = "/halls";

    @Override
    public List<HallModel> getAllHallModels() {
        RestTemplate restTemplate = new RestTemplate();
        HallModel[] hallModels = restTemplate.getForObject(backend + path, HallModel[].class);
        return hallModels == null ? Collections.emptyList() : Arrays.asList(hallModels);
    }
}
