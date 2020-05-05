package com.cinema.api.controller;

import com.cinema.api.model.LoginModel;
import com.cinema.api.model.UserModel;
import com.cinema.api.service.LoginModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/logins")
public class LoginModelController {

    @Autowired
    private LoginModelService loginModelService;

    @GetMapping("/{id}")
    private LoginModel getLoginModelById(@PathVariable Long id){
        return loginModelService.getLoginModelById(id);
    }

    @GetMapping("/emails/{email}")
    private LoginModel getLoginModelByEmail(@PathVariable String email){
        return loginModelService.getLoginModelByEmail(email);
    }

    @GetMapping("/users/{email}")
    private UserModel getUserModelByLoginModel(@PathVariable String email){
        return loginModelService.getUserModelByEmail(email);
    }

    @PostMapping("")
    private LoginModel saveLoginModel(@RequestBody LoginModel login){
        return loginModelService.saveLoginModel(login);
    }
}
