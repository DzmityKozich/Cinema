package com.backend.service;

import com.backend.entity.Billing;

public interface BillingService {
    Billing getBillingById(Long id);
    Billing getByUser(Long id);
    Billing saveBilling(Billing billing);
    Billing putMoney(Long userId, double money);
    void deleteBillingById(Long id);
}
