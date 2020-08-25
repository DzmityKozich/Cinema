package com.backend.repository;

import com.backend.entity.Cinema;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;

import java.util.List;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Long> {
    Cinema findByIdCinema(Long id);
    Page<Cinema> findAll(Pageable pageable);
    List<Cinema> findAll();
}
