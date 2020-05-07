package com.cinema.api.controller;

import com.cinema.api.model.LoginModel;
import com.cinema.api.model.UserModel;
import com.cinema.api.service.LoginModelService;
import com.cinema.api.service.ValidatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/logins")
public class LoginModelController {

    @Autowired
    private LoginModelService loginModelService;

    @Autowired
    private ValidatorService validatorService;

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
    private ResponseEntity saveLoginModel(@RequestBody LoginModel login){
        if (login != null && validatorService.validator(login, loginModelService.getAllLoginModels())) {
            return  ResponseEntity.ok(loginModelService.saveLoginModel(login));
        } else return ResponseEntity.badRequest().body("this user is exist");
    }
}
