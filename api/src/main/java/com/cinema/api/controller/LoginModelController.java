package com.cinema.api.controller;

import com.cinema.api.model.LoginModel;
import com.cinema.api.model.UserModel;
import com.cinema.api.service.LoginModelService;
import com.cinema.api.service.ValidatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping("")
    private List<LoginModel> getAllLoginModels(){
        return loginModelService.getAllLoginModels();
    }

    @GetMapping("/emails/{email}")
    private LoginModel getLoginModelByEmail(@PathVariable String email){
        return loginModelService.getLoginModelByEmail(email);
    }

    @GetMapping("/users/{email}")
    private UserModel getUserModelByLoginModel(@PathVariable String email){
        return loginModelService.getUserModelByEmail(email);
    }

    @GetMapping("/login/{login}")
    private boolean validator(@PathVariable String login){
        return validatorService.validator(login, loginModelService.getAllLoginModels());
    }

    @PostMapping("")
    private LoginModel saveLoginModel(@Valid @RequestBody LoginModel login){
        return loginModelService.saveLoginModel(login);
    }
}
