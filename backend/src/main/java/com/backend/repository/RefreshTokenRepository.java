package com.backend.repository;

import com.backend.entity.RefreshToken;
import com.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    RefreshToken findByToken(String token);
    RefreshToken findByUser(User user);
    void deleteByToken(String token);
}
