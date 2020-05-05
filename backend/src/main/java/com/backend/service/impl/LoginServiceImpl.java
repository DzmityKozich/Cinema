package com.backend.service.impl;

import com.backend.entity.Login;
import com.backend.entity.User;
import com.backend.repository.LoginRepository;
import com.backend.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginRepository loginRepository;

    @Override
    public Login getById(Long id) {
        return loginRepository.findByIdLogin(id);
    }

    @Override
    public Login getLoginByEmail(String email) {
        return loginRepository.findByEmail(email);
    }

    @Override
    public Login saveLogin(Login login) {
        return loginRepository.save(login);
    }

    @Override
    public User getUserByEmail(String email) {
        return loginRepository.findByEmail(email).getLoginUser();
    }

    @Override
    public void deleteLoginById(Long id) {
        loginRepository.deleteById(id);
    }
}
