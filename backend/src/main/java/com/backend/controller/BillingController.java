package com.backend.controller;

import com.backend.entity.Billing;
import com.backend.service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/billings")
public class BillingController {

    @Autowired
    private BillingService billingService;

    @GetMapping("/{id}")
    private Billing getBillingById(@PathVariable Long id){
        return billingService.getBillingById(id);
    }

    @GetMapping("/users/{id}")
    private Billing getBillingByUser(@PathVariable Long id){
        return billingService.getByUser(id);
    }

    @PostMapping("")
    private Billing saveBilling(@RequestBody Billing billing){
        return billingService.saveBilling(billing);
    }

    @PostMapping("/money/{userId}")
    private Billing putMoney(@PathVariable Long userId, @RequestBody double money){
        return billingService.putMoney(userId, money);
    }

    @DeleteMapping("/{id}")
    private void deleteBillingById(@PathVariable Long id){
        billingService.deleteBillingById(id);
    }
}
