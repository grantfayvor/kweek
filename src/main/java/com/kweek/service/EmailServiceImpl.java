package com.kweek.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * Created by Harrison on 2017-06-16.
 */

@Component
//public class EmailServiceImpl implements EmailService{
class EmailServiceImpl {

    @Autowired
    public JavaMailSender mailSender;

//    public void sendSimpleMessage(String recipient, String subject, String message){

    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }

    public void sendMessageToMultiple(String[] recipients, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipients);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }
}
