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

    @GetMapping("/email/{email}")
    private UserModel getUserModelByLoginModel(@PathVariable String email){
        return loginModelService.getUserModelByLoginModel(email);
    }

    @PostMapping("")
    private LoginModel saveLoginModel(@RequestBody LoginModel login){
        return loginModelService.saveLoginModel(login);
    }
}
