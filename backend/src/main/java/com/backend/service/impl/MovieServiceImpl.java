package com.backend.service.impl;

import com.backend.entity.Movie;
import com.backend.pagination.PaginatorPage;
import com.backend.repository.MovieRepository;
import com.backend.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public Movie getById(Long id) {
        return movieRepository.findByIdMovie(id);
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public PaginatorPage<Movie> getAllMoviesByPage(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("idMovie").descending());
        Page<Movie> page = movieRepository.findAll(pageable);
        return new PaginatorPage<>(page.getContent(), page.getNumber(), page.getSize(), page.isFirst(), page.isLast(), page.getTotalPages(), page.getTotalElements());
    }

    @Override
    public PaginatorPage<Movie> getAllMoviesByGenre(String genre, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("idMovie").descending());
        Page<Movie> page = movieRepository.findAllByGenre(genre, pageable);
        return new PaginatorPage<>(page.getContent(), page.getNumber(), page.getSize(), page.isFirst(), page.isLast(), page.getTotalPages(), page.getTotalElements());
    }

    @Override
    public void deleteMovieById(Long id) {
        movieRepository.deleteById(id);
    }
}
