package com.backend.service.impl;

import com.backend.entity.Billing;
import com.backend.entity.Place;
import com.backend.repository.BillingRepository;
import com.backend.repository.PlaceRepository;
import com.backend.repository.SeanceRepository;
import com.backend.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private BillingRepository billingRepository;

    @Autowired
    private SeanceRepository seanceRepository;

    @Override
    public Place getPlaceById(Long id) {
        return placeRepository.findByIdPlace(id);
    }

    @Override
    public List<Place> getAllPlaces() {
        return placeRepository.findAll();
    }

    @Override
    public Place savePlace(Place place) {
        return placeRepository.save(place);
    }

    @Override
    public List<Place> getAllBySeance(Long id) {
        return placeRepository.findAllBySeance(seanceRepository.findByIdSeance(id));
    }

    @Override
    public List<Place> getAllPlacesByBilling(Long id) {
        List<Place> places = new ArrayList<>();
        LocalDate now = LocalDate.now();
        for (Place place:
                placeRepository.findAllByBilling(billingRepository.findByIdBilling(id))) {
            if (place.getSeance().getDate().compareTo(now) == 0 && place.getSeance().getTime().isAfter(LocalTime.now()) ||
            place.getSeance().getDate().compareTo(now) > 0) {
                places.add(place);
            }
        }
        return places;
    }

    @Override
    public void clearSelectedPlaces(List<Place> places) {
        places.forEach(place -> {
            place.setState("Vacancy");
            place.setBilling(null);
        });
        placeRepository.saveAll(places);
    }

    @Override
    public void takePlace(List<Place> places) {
        Billing billing = places.get(0).getBilling();
        double sum = 0.0;
        for (Place place: places) {
            place.setState("Taken");
            sum += place.getSeance().getPrice();
        }
        billing.setBalance(billing.getBalance() - sum);
        billingRepository.save(billing);
        placeRepository.saveAll(places);
    }

    @Override
    public void deletePlaceById(Long id) {
        placeRepository.deleteById(id);
    }
}
