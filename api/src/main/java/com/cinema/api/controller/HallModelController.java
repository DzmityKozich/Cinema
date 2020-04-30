package com.cinema.api.controller;

import com.cinema.api.model.HallModel;
import com.cinema.api.service.HallModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/halls")
public class HallModelController {

    @Autowired
    private HallModelService hallModelService;

    @GetMapping("")
    private List<HallModel> gatAllHalls(){
        return hallModelService.getAllHallModels();
    }
}
