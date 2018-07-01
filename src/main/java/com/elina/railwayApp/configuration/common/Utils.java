package com.elina.railwayApp.configuration.common;

import com.elina.railwayApp.model.Message;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public static String encodePassword(String password) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    public static boolean checkTransfer(Date dateDeparture, Date dateArrival, int deltaMin, int deltaMax) {
        Long delta = (dateArrival.getTime() - dateDeparture.getTime()) / (1000 * 60);
        return (delta >= deltaMin && delta <= deltaMax) ? true : false;
    }

    public static Date getNextDay(Date date) {
        Date newDate = new Date();
        newDate.setTime(date.getTime() + (long) 1000 * 24 * 60 * 60);
        return newDate;
    }

    public static Date parseToDate(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.parse(date);
    }

    public static Date parseToDateTime(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.parse(date);
    }

    public static String getContext() throws IOException {
        File file = ResourceUtils.getFile("classpath:themesMessage/templateForEmailWelcomeMessage.txt");
        return new String(Files.readAllBytes(file.toPath()));
    }
}
