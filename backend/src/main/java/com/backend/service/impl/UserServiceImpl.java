package com.backend.service.impl;

import com.backend.entity.Billing;
import com.backend.entity.User;
import com.backend.repository.BillingRepository;
import com.backend.repository.UserRepository;
import com.backend.service.BillingService;
import com.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BillingRepository billingRepository;

    @Override
    public User getUserById(Long id) {
        return userRepository.findByIdUser(id);
    }

    @Override
    public User saveUser(User user) {
        User usr = userRepository.save(user);
        Billing billing = new Billing(usr);
        billingRepository.save(billing);
        return usr;
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
