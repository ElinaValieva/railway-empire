package com.elina.railwayApp.service;

import com.elina.railwayApp.model.Message;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

@Log4j
@Component
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;

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

    public void sendMimeMessage(Message message) throws MessagingException {
        MimeMessage mimeMailMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
        mimeMessageHelper.setFrom("railway.t-systems@mail.ru");
        mimeMailMessage.setContent(message.getContext(), "text/html");
        mimeMessageHelper.setTo(message.getAddressee());
        mimeMessageHelper.setSubject(message.getSubject());
        if (message.getText() != null)
            mimeMessageHelper.setText(message.getText());
        javaMailSender.send(mimeMailMessage);
    }

    public void sendMimeFile(Message message, String filename) throws MessagingException {
        MimeMessage mimeMailMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
        mimeMessageHelper.setFrom("railway.t-systems@mail.ru");
        mimeMessageHelper.setTo(message.getAddressee());
        mimeMessageHelper.setSubject(message.getSubject());
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        Multipart multipart = new MimeMultipart();
        BodyPart messageBodyPart = new MimeBodyPart();
        DataSource source = new FileDataSource(filename);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(filename);
        multipart.addBodyPart(messageBodyPart);
        if (message.getText() != null) {
            mimeMailMessage.setContent(multipart);
            mimeMessageHelper.setText(message.getText());
        }
        javaMailSender.send(mimeMailMessage);
    }

}

