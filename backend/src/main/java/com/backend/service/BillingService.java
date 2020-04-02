package com.backend.service;

import com.backend.entity.Billing;

public interface BillingService {
    Billing getBillingById(Long id);
    Billing getByUser(Long id);
    Billing saveBilling(Billing billing);
    void deleteBillingById(Long id);
}
