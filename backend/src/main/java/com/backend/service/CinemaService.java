package com.backend.service;

import com.backend.entity.Cinema;
import com.backend.pagination.PaginatorPage;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CinemaService {
    Cinema getById(Long id);
    PaginatorPage<Cinema> getAllCinemasByPage(int pageNumber, int pageSize);
    List<Cinema> getAllCinemas();
    Cinema saveCinema(Cinema cinema);
    void deleteCinemaById(Long id);
}
