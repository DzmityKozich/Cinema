package com.cinema.api.service;

import com.cinema.api.mail.MailModel;

public interface MailService {

    void sendMail(MailModel mailModel);
    void sendMailForAllUsers(MailModel mailModel) throws InterruptedException;

}
