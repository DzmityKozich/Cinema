package com.backend.repository;

import com.backend.entity.Billing;
import com.backend.entity.Place;
import com.backend.entity.Seance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
    Place findByIdPlace(Long id);
    List<Place> findAllBySeance(Seance seance);
    List<Place> findAllByBilling(Billing billing);
}
