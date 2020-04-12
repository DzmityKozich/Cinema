package com.cinema.api.service.impl;

import com.cinema.api.model.CinemaModel;
import com.cinema.api.pagination.PaginatorPageModel;
import com.cinema.api.service.CinemaModelService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CinemaModelServiceImpl implements CinemaModelService {

    @Value("${backend.server.url}")
    private String backend;

    private final String path = "/cinemas/";

    @Override
    public CinemaModel getCinemaById(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backend + path + id, CinemaModel.class);
    }

    @Override
    public PaginatorPageModel getAllCinemaModels(int pageNumber, int pageSize) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backend + path + "?pageNumber=" + pageNumber + "&pageSize=" + pageSize, PaginatorPageModel.class);
    }

    @Override
    public CinemaModel saveCinema(CinemaModel cinemaModel) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backend + path, cinemaModel, CinemaModel.class).getBody();
    }
}
