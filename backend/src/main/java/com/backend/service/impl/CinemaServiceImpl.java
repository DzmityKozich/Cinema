package com.backend.service.impl;

import com.backend.entity.Cinema;
import com.backend.pagination.PaginatorPage;
import com.backend.repository.CinemaRepository;
import com.backend.repository.SeanceRepository;
import com.backend.service.CinemaService;
import com.backend.service.SeanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CinemaServiceImpl implements CinemaService {

    @Autowired
    private CinemaRepository cinemaRepository;

    @Override
    public Cinema getById(Long id) {
        return cinemaRepository.findByIdCinema(id);
    }

    @Override
    public PaginatorPage<Cinema> getAllCinemasByPage(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("idCinema").descending());
        Page<Cinema> page = cinemaRepository.findAll(pageable);
        return new PaginatorPage<>(page.getContent(), page.getNumber(), page.getSize(), page.isFirst(), page.isLast(), page.getTotalPages(), page.getTotalElements());
    }

    @Override
    public List<Cinema> getAllCinemas() {
        return cinemaRepository.findAll();
    }

    @Override
    public Cinema saveCinema(Cinema cinema) {
        return cinemaRepository.save(cinema);
    }

    @Override
    public void deleteCinemaById(Long id) {
        cinemaRepository.deleteById(id);
    }
}

