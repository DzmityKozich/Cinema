package com.cinema.api.service.impl;

import com.cinema.api.model.BillingModel;
import com.cinema.api.service.BillingModelService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BillingModelServiceImpl implements BillingModelService {

    @Value("${backend.server.url}")
    private String backend;

    private final String path = "/billings/";

    @Override
    public BillingModel getBillingModelById(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backend + path + id, BillingModel.class);
    }

    @Override
    public BillingModel getByUserModel(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backend + path + "/users/" + id, BillingModel.class);
    }

    @Override
    public BillingModel saveBillingModel(BillingModel billingModel) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backend + path, billingModel, BillingModel.class).getBody();
    }

    @Override
    public BillingModel putMoney(Long userId, double money) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backend + path + "/money/" + userId, money, BillingModel.class).getBody();
    }
}
