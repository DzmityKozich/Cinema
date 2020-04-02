package com.backend.service;

import com.backend.entity.User;

public interface UserService {
    User getUserById(Long id);
    User saveUser(User user);
    void deleteUserById(Long id);
}
