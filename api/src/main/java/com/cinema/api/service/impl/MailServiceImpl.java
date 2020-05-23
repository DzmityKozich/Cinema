package com.cinema.api.service.impl;

import com.cinema.api.mail.MailConfig;
import com.cinema.api.mail.MailModel;
import com.cinema.api.model.LoginModel;
import com.cinema.api.service.LoginModelService;
import com.cinema.api.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.List;

@EnableAsync
@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private MailConfig mailConfig;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    protected LoginModelService loginModelService;

    @Override
    public void sendMail(MailModel mailModel) {
        javaMailSender.send(mailConfig.madeMessage(mailModel));
    }

    @Override
    @Async
    public void sendMailForAllUsers(MailModel mailModel) throws InterruptedException {
        List<LoginModel> list = loginModelService.getAllLoginModels();
        SimpleMailMessage[] simpleMailMessages = new SimpleMailMessage[list.size()];
        for (int i = 0; i < list.size(); i++) {
            simpleMailMessages[i] = mailConfig.madeMessage(list.get(i).getEmail(), mailModel);
            send(simpleMailMessages[i], 0, 90);
        }
    }

    private void send(SimpleMailMessage message, int count, int sec) throws InterruptedException {
        try {
            javaMailSender.send(message);
        } catch (MailSendException e) {
            if (count < sec){
                Thread.sleep(1000);
                send(message, count++, sec);
            } else {
                throw e;
            }
        }
    }

}
