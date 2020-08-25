package com.cinema.api.service;

import com.cinema.api.model.HallModel;

import java.util.List;

public interface HallModelService {

    List<HallModel> getAllHallModels();
    HallModel[] saveHallModels(HallModel[] hallModels);
}
