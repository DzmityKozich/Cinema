package com.backend.repository;

import com.backend.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Movie findByIdMovie(Long id);
    Page<Movie> findAll(Pageable pageable);
    List<Movie> findAll();
    List<Movie> findAllByGenre(String genre, Pageable pageable);
}
