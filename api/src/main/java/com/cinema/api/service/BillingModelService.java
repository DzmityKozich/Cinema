package com.cinema.api.service;

import com.cinema.api.model.BillingModel;

public interface BillingModelService {
    BillingModel getBillingModelById(Long id);
    BillingModel getByUserModel(Long id);
    BillingModel saveBillingModel(BillingModel billingModel);
    BillingModel putMoney(Long userId, double money);
}
