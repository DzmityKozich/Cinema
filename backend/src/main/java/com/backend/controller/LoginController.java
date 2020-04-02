package com.backend.controller;

import com.backend.entity.Login;
import com.backend.entity.User;
import com.backend.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/logins")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/{id}")
    private Login getLoginById(@PathVariable Long id){
        return loginService.getById(id);
    }

    @PostMapping("")
    private Login saveLogin(@RequestBody Login login){
        return loginService.saveLogin(login);
    }

    @GetMapping("/emails/{email}")
    private User getUserByEmail(@PathVariable String email){
        return loginService.getUserByLogin(email);
    }

    @DeleteMapping("/{id}")
    private void deleteLoginById(@PathVariable Long id){
        loginService.deleteLoginById(id);
    }
}
