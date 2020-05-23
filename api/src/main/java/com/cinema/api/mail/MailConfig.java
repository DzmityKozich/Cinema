package com.cinema.api.mail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class MailConfig {

    @Value("${spring.mail.host}")
    private String host;
    @Value("${spring.mail.port}")
    private Integer port;
    @Value("${spring.mail.username}")
    private String username;
    @Value("${spring.mail.password}")
    private String password;

    public SimpleMailMessage madeMessage(MailModel mailModel) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailModel.getTo());
        message.setFrom(mailModel.getFrom());
        message.setSubject(mailModel.getSubject());
        message.setText(mailModel.getText());
        return message;
    }

    public SimpleMailMessage madeMessage(String to, MailModel mailModel) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setFrom(mailModel.getFrom());
        message.setSubject(mailModel.getSubject());
        message.setText(mailModel.getText());
        return message;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "MailConfig{" +
                "host='" + host + '\'' +
                ", port=" + port +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
