package com.elina.railwayApp.service;

import com.elina.railwayApp.model.Message;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
public interface MailService {

    void sendSimpleMessage(Message message);

    void sendMimeMessage(Message message) throws MessagingException;
}
