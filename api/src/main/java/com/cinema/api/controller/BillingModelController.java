package com.cinema.api.controller;

import com.cinema.api.model.BillingModel;
import com.cinema.api.service.BillingModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/billings")
public class BillingModelController {

    @Autowired
    private BillingModelService billingModelService;

    @GetMapping("/{id}")
    private BillingModel getBillingModelById(@PathVariable Long id){
        return billingModelService.getBillingModelById(id);
    }

    @GetMapping("/users/{id}")
    private BillingModel getByUserModel(@PathVariable Long id){
        return billingModelService.getByUserModel(id);
    }

    @PostMapping("")
    private BillingModel saveBillingModel(@RequestBody BillingModel billingModel){
        return billingModelService.saveBillingModel(billingModel);
    }

    @PostMapping("/money/{userId}")
    private ResponseEntity putMoney(@PathVariable Long userId, @RequestBody double money){
        if (money > 0) {
            return ResponseEntity.ok(billingModelService.putMoney(userId, money));
        } else return ResponseEntity.badRequest().body(">0!");
    }
}
