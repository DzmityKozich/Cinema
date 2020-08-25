package com.cinema.api.controller;

import com.cinema.api.model.HallModel;
import com.cinema.api.service.HallModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("")
    private HallModel[] saveHallModel(@RequestBody HallModel[] hallModels) {
        return hallModelService.saveHallModels(hallModels);
    }
}
