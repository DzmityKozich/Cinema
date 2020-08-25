package com.backend.controller;

import com.backend.entity.Hall;
import com.backend.service.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/halls")
public class HallController {

    @Autowired
    private HallService hallService;

    @GetMapping("")
    private  List<Hall> getAllHalls(){
        return hallService.getAllHalls();
    }

    @GetMapping("/{id}")
    private Hall getHallById(@PathVariable Long id){
        return hallService.getHallById(id);
    }

    @GetMapping("/cinemas/{id}")
    private List<Hall> getAllByCinema(@PathVariable Long id){
        return hallService.getAllByCinema(id);
    }

//    @PostMapping("")
//    private Hall saveHall(@Valid @RequestBody Hall hall){
//        return hallService.saveHall(hall);
//    }

    @PostMapping("")
    private List<Hall> saveHalls(@RequestBody Hall[] halls) {
        return hallService.saveHalls(Arrays.asList(halls));
    }

    @DeleteMapping("/{id}")
    private void deleteCinemaById(@PathVariable Long id){
        hallService.deleteHallById(id);
    }
}
