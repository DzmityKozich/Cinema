package com.cinema.api.service.impl;

import com.cinema.api.model.MovieModel;
import com.cinema.api.pagination.PaginatorPageModel;
import com.cinema.api.service.MovieModelService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieModelServiceImpl implements MovieModelService {

    @Value("${backend.server.url}")
    private String backend;

    private final String path = "/movies/";

    @Override
    public PaginatorPageModel getAllMovieModels(int pageNumber, int pageSize) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backend + path + "?pageNumber=" + pageNumber + "&pageSize=" + pageSize, PaginatorPageModel.class);
    }

    @Override
    public MovieModel getMovieModelById(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backend + path + id, MovieModel.class);
    }

    @Override
    public MovieModel saveMovieModel(MovieModel movie) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backend + "/movies/", movie, MovieModel.class).getBody();
    }
}
