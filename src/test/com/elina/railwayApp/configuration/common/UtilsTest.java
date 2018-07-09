package com.elina.railwayApp.configuration.common;

import org.junit.Test;

import java.text.ParseException;
import java.util.Date;

import static org.junit.Assert.*;

public class UtilsTest {

    @Test
    public void checkCurrentDay() throws ParseException {
        Date date = Utils.parseToDate("2018-07-06 17:30:00");
        assertTrue(Utils.checkCurrentDay(date));
    }

    @Test
    public void checkForCurrentDayForBookingTicket() throws ParseException {
        Date date = Utils.parseToDateTime("2018-07-09 22:30:00");
        assertTrue(Utils.checkForCurrentDayForBookingTicket(date));
    }
}