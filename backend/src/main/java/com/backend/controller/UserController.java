package com.backend.controller;

import com.backend.entity.User;
import com.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    private User getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @PostMapping("")
    private User saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @DeleteMapping("/{id}")
    private void deleteUserById(@PathVariable Long id){
        userService.deleteUserById(id);
    }
}
