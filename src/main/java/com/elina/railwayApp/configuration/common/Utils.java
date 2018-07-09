package com.elina.railwayApp.configuration.common;

import lombok.extern.log4j.Log4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Log4j
public class Utils {
    public static String encodePassword(String password) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    public static boolean checkTransfer(Date dateDeparture, Date dateArrival, int deltaMin, int deltaMax) {
        Long delta = (dateArrival.getTime() - dateDeparture.getTime()) / (1000 * 60);
        return (delta >= deltaMin && delta <= deltaMax) ? true : false;
    }

    public static Date getNextDay(String dateForNextDay) throws ParseException {
        Date date = parseToDate(dateForNextDay);
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

    public static String getDelay(String dateDeparture, String dateArrival) throws ParseException {
        Date dateDepartureForDelay = parseToDate(dateDeparture);
        Date dateArrivalForDelay = parseToDate(dateArrival);
        Long delay = dateArrivalForDelay.getTime() - dateDepartureForDelay.getTime();
        String result = "";
        int min = (int) (delay / (1000 * 60));
        int hour = (int) (delay / (1000 * 60 * 60));
        int days = (int) (delay / (1000 * 60 * 60 * 24));
        if (hour != 0)
            result = hour + " hours " + min + " min" + result;
        if (days != 0)
            result = days + " day " + result;
        log.info("IMPORTANT " + result);
        return result;
    }

    public static boolean checkCurrentDay(Date date) {
        return date.before(new Date());
    }

    public static boolean checkForCurrentDayForBookingTicket(Date date) {
        Date currentDay = new Date();
        return (!checkCurrentDay(date) && (date.getTime() - currentDay.getTime()) / (1000 * 60) > 10);
    }
}
