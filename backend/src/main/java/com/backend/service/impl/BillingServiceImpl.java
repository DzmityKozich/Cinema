package com.backend.service.impl;

import com.backend.entity.Billing;
import com.backend.repository.BillingRepository;
import com.backend.repository.UserRepository;
import com.backend.service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillingServiceImpl implements BillingService {

    @Autowired
    private BillingRepository billingRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Billing getBillingById(Long id) {
        return billingRepository.findByIdBilling(id);
    }

    @Override
    public Billing getByUser(Long id) {
        return billingRepository.findByBillingUser(userRepository.findByIdUser(id));
    }

    @Override
    public Billing saveBilling(Billing billing) {
        return billingRepository.save(billing);
    }

    @Override
    public Billing putMoney(Long userId, double money) {
        Billing billing = getByUser(userId);
        billing.setBalance(billing.getBalance() + money);
        return billingRepository.save(billing);
    }

    @Override
    public void deleteBillingById(Long id) {
        billingRepository.deleteById(id);
    }
}
