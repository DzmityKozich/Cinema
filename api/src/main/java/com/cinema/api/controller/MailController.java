package com.cinema.api.controller;

import com.cinema.api.mail.MailModel;
import com.cinema.api.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mail")
public class MailController {

    @Autowired
    private MailService mailService;

    @PostMapping("")
    private void sendMail(@RequestBody MailModel mailModel) {
        mailService.sendMail(mailModel);
    }

    @PostMapping("/all")
    private void sendMailForAllUsers(@RequestBody MailModel mailModel) throws InterruptedException {
        mailService.sendMailForAllUsers(mailModel);
    }
}
