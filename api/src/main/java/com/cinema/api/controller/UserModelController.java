package com.cinema.api.controller;

import com.cinema.api.model.UserModel;
import com.cinema.api.service.UserModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserModelController {

    @Autowired
    private UserModelService userModelService;

    @GetMapping("/{id}")
    private UserModel getUserModelById(@PathVariable Long id){
        return userModelService.getUserModelById(id);
    }

    @PostMapping("")
    private UserModel saveUserModel(@RequestBody UserModel userModel){
        return userModelService.saveUserModel(userModel);
    }
}
