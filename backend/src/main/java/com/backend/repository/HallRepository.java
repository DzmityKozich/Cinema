package com.backend.repository;

import com.backend.entity.Cinema;
import com.backend.entity.Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HallRepository extends JpaRepository<Hall, Long> {
    Hall findByIdHall(Long id);
    List<Hall> findAllByCinema(Cinema cinema);
}
