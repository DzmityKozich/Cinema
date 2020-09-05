package com.backend.repository;

import com.backend.entity.Login;
import com.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<Login, Long> {
    Login findByIdLogin(Long id);
    Login findByEmail(String email);
    Login findByLoginUser(User user);
}
