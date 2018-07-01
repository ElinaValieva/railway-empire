package com.elina.railwayApp.service.Implementation;

import com.elina.railwayApp.model.Message;
import com.elina.railwayApp.service.MailService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Log4j
@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendSimpleMessage(Message message) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("railway.t-systems@mail.ru");
        simpleMailMessage.setTo(message.getAddressee());
        simpleMailMessage.setSubject(message.getSubject());
        simpleMailMessage.setText(message.getText());
        try {
            javaMailSender.send(simpleMailMessage);
            log.info("SEND MESSAGE TO " + message.getAddressee());
        } catch (Exception ex) {
            ex.printStackTrace();
            log.warn("CAN'T SEND MESSAGE TO " + message.getAddressee());
        }
    }

    @Override
    public void sendMimeMessage(Message message) {
        MimeMessage mimeMailMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
            mimeMessageHelper.setFrom("railway.t-systems@mail.ru");
            mimeMailMessage.setContent(message.getContext(), "text/html");
            mimeMessageHelper.setTo(message.getAddressee());
            mimeMessageHelper.setSubject(message.getSubject());
            if (message.getText() != null)
                mimeMessageHelper.setText(message.getText());
            javaMailSender.send(mimeMailMessage);
            log.info("SEND MESSAGE TO " + message.getAddressee());
        } catch (MessagingException e) {
            e.printStackTrace();
            log.warn("CAN'T SEND MESSAGE TO " + message.getAddressee());
        }
    }
}

