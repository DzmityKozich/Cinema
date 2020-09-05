package com.backend.controller;

import com.backend.entity.Login;
import com.backend.entity.User;
import com.backend.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/logins")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/{id}")
    private Login getLoginById(@PathVariable Long id){
        return loginService.getById(id);
    }

    @GetMapping("")
    private List<Login> getAllLogins(){
        return loginService.getAllLogins();
    }

    @GetMapping("/emails/{email}")
    private Login getLoginByEmail(@PathVariable String email){
        return loginService.getLoginByEmail(email);
    }

    @PostMapping("")
    private Login saveLogin(@Valid @RequestBody Login login){
        return loginService.saveLogin(login);
    }

    @GetMapping("/users/{email}")
    private User getUserByEmail(@PathVariable String email){
        return loginService.getUserByEmail(email);
    }

    @PostMapping("/users")
    private String getEmailByUser(@RequestBody User user) {
        return loginService.getEmailByUser(user);
    }

    @DeleteMapping("/{id}")
    private void deleteLoginById(@PathVariable Long id){
        loginService.deleteLoginById(id);
    }
}
